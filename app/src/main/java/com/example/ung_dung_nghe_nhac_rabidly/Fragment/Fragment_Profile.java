package com.example.ung_dung_nghe_nhac_rabidly.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ung_dung_nghe_nhac_rabidly.Activity.DangKyActivity;
import com.example.ung_dung_nghe_nhac_rabidly.Activity.HomeActivity;
import com.example.ung_dung_nghe_nhac_rabidly.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Profile extends Fragment {
    Button btn;
    TextView acc, pass, name, url;
    HomeActivity hm;
    CircleImageView imguser;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        AnhXa();
        Init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangKyActivity.class);
                startActivity(intent);
                hm.finish();
            }
        });
        return  view;
    }

    private void Init() {
        name = view.findViewById(R.id.tennguoidung);
        hm = (HomeActivity) getActivity();
        name.setText(hm.getName());
        Picasso.get().load(hm.getUrl()).into(imguser);
    }

    private void AnhXa() {
        btn = view.findViewById(R.id.btndangxuat);
        name = view.findViewById(R.id.tennguoidung);
        imguser = view.findViewById(R.id.imageviewuserprofile);
    }

}
