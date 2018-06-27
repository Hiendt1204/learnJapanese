package com.example.duongthuhien.kltn.Minna;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Adapter.Kaiwa_adapter;
import com.example.duongthuhien.kltn.Adapter.ThamKhao_Frag_adapter;
import com.example.duongthuhien.kltn.Model.Kaiwa_frag;
import com.example.duongthuhien.kltn.Model.ThamKhao_frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84973 on 6/15/2018.
 */

public class Kaiwa_Fragment extends Fragment {
    List<Kaiwa_frag> kaiwaFragList=new ArrayList<Kaiwa_frag>();
    int posDSBaiHoc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_kaiwa,container,false);

        posDSBaiHoc = getArguments().getInt("Bai");

        ArrayList<Kaiwa_frag> listKaiwa=getListKaiwa();
        Kaiwa_frag headerKaiwa = listKaiwa.get(0);
        listKaiwa.remove(0);

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.item_kaiwa_header, null, false);
        TextView headerJWord = headerView.findViewById(R.id.tv_Jword);
        headerJWord.setText(headerKaiwa.getStrNameK());

        TextView headerPhienAm = headerView.findViewById(R.id.tv_PhienAm);
        headerPhienAm.setText(headerKaiwa.getStrNamJ());

        TextView headerNameJ = headerView.findViewById(R.id.tv_Vword);
        headerNameJ.setText(headerKaiwa.getStrVword());

        ListView lv_ThamKhao_fragment=(ListView)view.findViewById(R.id.lv_Kaiwa);
        lv_ThamKhao_fragment.addHeaderView(headerView);
        lv_ThamKhao_fragment.setAdapter(new Kaiwa_adapter(getActivity(),listKaiwa));


        return view;
    }

    private ArrayList<Kaiwa_frag> getListKaiwa() {
        ArrayList<Kaiwa_frag> kaiwaFragList = new ArrayList<Kaiwa_frag>();

        SQLiteDataController sqLiteDataController=new SQLiteDataController(this.getActivity());
        sqLiteDataController.open();
        SQLiteDatabase database=sqLiteDataController.getMyDatabase();

        kaiwaFragList=sqLiteDataController.getKaiwabylessionID_KW(posDSBaiHoc);
        for (Kaiwa_frag thamKhao_frag_frag :kaiwaFragList){}
        return kaiwaFragList;
    }
}
