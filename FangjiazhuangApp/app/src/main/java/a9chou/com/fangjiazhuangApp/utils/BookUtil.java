package a9chou.com.fangjiazhuangApp.utils;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import a9chou.com.fangjiazhuangApp.db.BookCatalogue;
import a9chou.com.fangjiazhuangApp.db.BookList;
import a9chou.com.fangjiazhuangApp.module.dao.Cache;
public class BookUtil {
    private static final String cachedPath = Environment.getExternalStorageDirectory() + "/treader/";
    //存储的字符数
    public static final int cachedSize = 30000;
    protected final ArrayList<Cache> myArray = new ArrayList<>();
    //目录
    private List<BookCatalogue> directoryList = new ArrayList<>();

    private String m_strCharsetName;
    private String bookName;
    private String bookPath;
    private long bookLen;
    private long position;
    private BookList bookList;

    public BookUtil() {
        File file = new File(cachedPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public synchronized void openBook(BookList bookList) throws IOException {
        this.bookList = bookList;
        //如果当前缓存不是要打开的书本就缓存书本同时删除缓存

        if (bookPath == null || !bookPath.equals(bookList.getBookpath())) {
            cleanCacheFile();
            this.bookPath = bookList.getBookpath();
            bookName = FileUtils.getFileName(bookPath);
            cacheBook();
        }
    }

    private void cleanCacheFile() {
        File file = new File(cachedPath);
        if (!file.exists()) {
            file.mkdir();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }


        }
    }

    public int next(boolean back) {
        position += 1;
        if (position > bookLen) {
            position = bookLen;
            return -1;
        }
        char result = current();
        if (back) {
            position -= 1;
        }
        return result;
    }

    public char[] nextLine() {
        if (position >= bookLen) {
            return null;
        }
        String line = "";
        while (position < bookLen) {
            int word = next(false);
            if (word == -1) {
                break;
            }
            char wordChar = (char) word;
            if ((wordChar + "").equals("\r") && (((char) next(true)) + "").equals("\n")) {
                next(false);
                break;
            }
            line += wordChar;
        }
        return line.toCharArray();
    }

    public char[] preLine() {
        if (position <= 0) {
            return null;
        }
        String line = "";
        while (position >= 0) {
            int word = pre(false);
            if (word == -1) {
                break;
            }
            char wordChar = (char) word;
            if ((wordChar + "").equals("\n") && (((char) pre(true)) + "").equals("\r")) {
                pre(false);
                break;
            }
            line = wordChar + line;
        }
        return line.toCharArray();
    }

    public char current() {
        int cachePos = 0;
        int pos = 0;
        int len = 0;
        for (int i = 0; i < myArray.size(); i++) {
            long size = myArray.get(i).getSize();
            if (size + len - 1 >= position) {
                cachePos = i;
                pos = (int) (position - len);
                break;
            }
            len += size;
        }

        char[] charArray = block(cachePos);
        return charArray[pos];
    }

    public int pre(boolean back) {
        position -= 1;
        if (position < 0) {
            position = 0;
            return -1;
        }
        char result = current();
        if (back) {
            position += 1;
        }
        return result;
    }

    public long getPosition() {
        return position;
    }

    public void setPostition(long position) {
        this.position = position;
    }

    //缓存书本
    private void cacheBook() throws IOException {
//        if (TextUtils.isEmpty(bookList.getCharset())) {
//            m_strCharsetName = FileUtils.getCharset(bookPath);
//            Log.d("bookutils", "cacheBook: "+FileUtils.getCharset(bookPath));
//            if (m_strCharsetName == null) {
//                m_strCharsetName = "utf-8";
//            }
//            ContentValues values = new ContentValues();
//            values.put("charset",m_strCharsetName);
//            DataSupport.update(BookList.class,values,bookList.getId());
//        }else{
//            m_strCharsetName = bookList.getCharset();
//        }
//网络请求
        URL url = new URL(bookPath);
        URLConnection urlcon = url.openConnection();

         InputStreamReader reader = new InputStreamReader(urlcon.getInputStream(),"UTF-8");
         int index = 0;
        bookLen = 0;
        directoryList.clear();
        myArray.clear();
        while (true) {
            char[] buf = new char[cachedSize];
            int result = reader.read(buf);
            if (result == -1) {
                reader.close();
                break;
            }

            String bufStr = new String(buf);
            bufStr = bufStr.replaceAll("\r\n+\\s*", "\r\n\u3000\u3000");
            bufStr = bufStr.replaceAll("\u0000", "");
            buf = bufStr.toCharArray();
            bookLen += buf.length;

            Cache cache = new Cache();
            cache.setSize(buf.length);
            cache.setData(new WeakReference<char[]>(buf));
            myArray.add(cache);
            try {
                File cacheBook = new File(fileName(index));

                Log.i("bbb", fileName(index));
                Log.i("ccc", cacheBook.exists() + "");
                if (!cacheBook.exists()) {
                    Log.i("ddd", "122");

                    cacheBook.createNewFile();
                }
                OutputStream out = new FileOutputStream(fileName(index));
                Log.i("ddd", out + "");
                final OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-16LE");
//                final OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
                writer.write(buf);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Error during writing " + fileName(index));
            }
            index++;
        }

        new Thread() {
            @Override
            public void run() {
                getChapter();
            }
        }.start();
    }

    //获取章节
    public synchronized void getChapter() {
        try {
            long size = 0;
            for (int i = 0; i < myArray.size(); i++) {
                char[] buf = block(i);
                String bufStr = new String(buf);
                String[] paragraphs = bufStr.split("\r\n");
                for (String str : paragraphs) {
                    if (str.length() <= 30 && (str.matches(".*第.{1,8}章.*") || str.matches(".*第.{1,8}节.*"))) {
                        BookCatalogue bookCatalogue = new BookCatalogue();
                        bookCatalogue.setBookCatalogueStartPos(size);
                        bookCatalogue.setBookCatalogue(str);
                        bookCatalogue.setBookpath(bookPath);
                        directoryList.add(bookCatalogue);
                    }
                    if (str.contains("\u3000\u3000")) {
                        size += str.length() + 2;
                    } else if (str.contains("\u3000")) {
                        size += str.length() + 1;
                    } else {
                        size += str.length();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BookCatalogue> getDirectoryList() {
        return directoryList;
    }

    public long getBookLen() {
        return bookLen;
    }

    protected String fileName(int index) {
        return cachedPath + bookName + index;
    }

    //获取书本缓存
    public char[] block(int index) {
        if (myArray.size() == 0) {
            return new char[1];
        }
        char[] block = myArray.get(index).getData().get();
        if (block == null) {
            try {
                File file = new File(fileName(index));
                int size = (int) file.length();
                if (size < 0) {
                    throw new RuntimeException("Error during reading " + fileName(index));
                }
                block = new char[size / 2];
                InputStreamReader reader =
                        new InputStreamReader(
                                new FileInputStream(file),"UTF-16LE");
//                                new FileInputStream(file),"UTF-8");
                if (reader.read(block) != block.length) {
                    throw new RuntimeException("Error during reading " + fileName(index));
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Error during reading " + fileName(index));
            }
            Cache cache = myArray.get(index);
            cache.setData(new WeakReference<char[]>(block));
        }
        return block;
    }

}
