package com.example.duongthuhien.kltn.Minna;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duongthuhien.kltn.Adapter.ThamKhao_Frag_adapter;
import com.example.duongthuhien.kltn.Adapter.TuMoiFrag_Adapter;
import com.example.duongthuhien.kltn.Model.ThamKhao_frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84973 on 6/14/2018.
 */

public class ThamKhaoFragment extends Fragment {
    List<ThamKhao_frag> thamKhaoFragList=new ArrayList<ThamKhao_frag>();
    int posDSBaiHoc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tham_khao,container,false);

        posDSBaiHoc = getArguments().getInt("Bai");
        Log.d("hiendt","posDSBaihoc " + posDSBaiHoc);
        ArrayList<ThamKhao_frag> listThamKhao=getListThamKhao();
        ListView lv_ThamKhao_fragment=(ListView)view.findViewById(R.id.lv_ThamKhao_Fragment);
        lv_ThamKhao_fragment.setAdapter(new ThamKhao_Frag_adapter(getActivity(),listThamKhao));


        return view;
    }

    private ArrayList<ThamKhao_frag> getListThamKhao() {
        ArrayList<ThamKhao_frag> tumoiFragList = new ArrayList<ThamKhao_frag>();

        SQLiteDataController sqLiteDataController=new SQLiteDataController(this.getActivity());
        sqLiteDataController.open();
        SQLiteDatabase database=sqLiteDataController.getMyDatabase();

        tumoiFragList=sqLiteDataController.getThamKhaobylessionID_TK(posDSBaiHoc);
        for (ThamKhao_frag thamKhao_frag_frag :thamKhaoFragList){}
        return tumoiFragList;
    }
}
