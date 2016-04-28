package com.zczczy.leo.fuwuwangapp.items;

import android.content.Context;
import android.widget.TextView;

import com.zczczy.leo.fuwuwangapp.R;
import com.zczczy.leo.fuwuwangapp.model.Notice;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Leo on 2016/4/28.
 */
@EViewGroup(R.layout.activity_notice_item)
public class NoticeItemView  extends ItemView<Notice> {


    @ViewById
    TextView txt_title,txt_date;

    public NoticeItemView(Context context) {
        super(context);
    }

    @Override
    protected void init(Object... objects) {

        txt_title.setText(_data.getTitle());

        txt_date.setText(_data.getDate());

    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}
