package com.example.duongthuhien.kltn.Minna;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.duongthuhien.kltn.Adapter.TuMoiFrag_Adapter;
import com.example.duongthuhien.kltn.Model.Tumoi_Frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;
import java.util.ArrayList;
import java.util.List;


public class TuMoiFragment extends Fragment {
    List<Tumoi_Frag> tumoiFragList=new ArrayList<Tumoi_Frag>();
    int posDSBaiHoc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tu_moi,container,false);

        posDSBaiHoc = getArguments().getInt("Bai");
        Log.d("hiendt","posDSBaihoc " + posDSBaiHoc);
        ArrayList<Tumoi_Frag> listTuMoi=getListTuMoi();
        ListView lv_TuMoi_fragment=(ListView)view.findViewById(R.id.lv_TuMoi_fragment);
        lv_TuMoi_fragment.setAdapter(new TuMoiFrag_Adapter(getActivity(),listTuMoi));


        return view;
    }

    private ArrayList<Tumoi_Frag> getListTuMoi() {
        ArrayList<Tumoi_Frag> tumoiFragList = new ArrayList<Tumoi_Frag>();

        SQLiteDataController sqLiteDataController=new SQLiteDataController(this.getActivity());
        sqLiteDataController.open();
        SQLiteDatabase database=sqLiteDataController.getMyDatabase();

        tumoiFragList=sqLiteDataController.getWordbylessionID_M(posDSBaiHoc);
        for (Tumoi_Frag tumoi_frag :tumoiFragList){}
        return tumoiFragList;
    }

}
