package a9chou.com.fangjiazhuangApp.module.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.CatalogueAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.db.BookCatalogue;
import a9chou.com.fangjiazhuangApp.utils.PageFactory;
import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class CatalogFragment extends BaseFragment {
    public static final String ARGUMENT = "argument";
    private PageFactory pageFactory;
    ArrayList<BookCatalogue> catalogueList = new ArrayList<>();
    @BindView(R.id.lv_catalogue)
    ListView mLvCatalogue;
    Unbinder unbinder;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_catalog;
    }

    @Override
    protected void initViews() {
        pageFactory = PageFactory.getInstance();
        catalogueList.addAll(pageFactory.getDirectoryList());
        CatalogueAdapter catalogueAdapter = new CatalogueAdapter(getContext(), catalogueList);
        catalogueAdapter.setCharter(pageFactory.getCurrentCharter());
        mLvCatalogue.setAdapter(catalogueAdapter);
        catalogueAdapter.notifyDataSetChanged();
        mLvCatalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pageFactory.changeChapter(catalogueList.get(position).getBookCatalogueStartPos());
                getActivity().finish();
            }
        });
    }

//    @Override
//    public boolean onBackPressed() {
//        return false;
//    }

    /**
     * 用于从Activity传递数据到Fragment
     *
     * @param bookpath
     * @return
     */
    public static CatalogFragment newInstance(String bookpath) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, bookpath);
        CatalogFragment catalogFragment = new CatalogFragment();
        catalogFragment.setArguments(bundle);
        return catalogFragment;
    }

}
