package com.example.ung_dung_nghe_nhac_rabidly.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ung_dung_nghe_nhac_rabidly.R;
import com.example.ung_dung_nghe_nhac_rabidly.Model.ResponseModel;

import com.example.ung_dung_nghe_nhac_rabidly.Service_API.APIService;
import com.example.ung_dung_nghe_nhac_rabidly.Service_API.Dataservice;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dialog_Dangky_Free extends AppCompatDialogFragment {

    TextInputLayout tk, mk, emaildangky;
    Button btnDangKy;
    ImageView imgclose;
    boolean accept = false, aceptmail = false;
    private ExampleDialogListener listener;
    String taikhoan="", matkhau="", email="";
    static int interval;
    static Timer timer;
    int delay = 1000, period = 1000, timeValue, code;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_dang_ky_free, null);
        tk = view.findViewById(R.id.edttaikhoan);
        mk = view.findViewById(R.id.edtmatkhau);
        emaildangky = view.findViewById(R.id.emaildangky);

        btnDangKy = view.findViewById(R.id.btndangKy);

        imgclose = view.findViewById(R.id.imageClose);
        builder.setView(view);



        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept = false;
                final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                loadingDialog.StartLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 3000);

                taikhoan = tk.getEditText().getText().toString().trim();
                matkhau = mk.getEditText().getText().toString().trim();
                if(taikhoan.trim().length() < 6 || taikhoan.trim().length() > 25){
                    Toast.makeText(getActivity(), "Độ dài tài khoản từ 6 -> 25 ký tự", Toast.LENGTH_LONG).show();
                }else {
                    checkUser(taikhoan);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (accept){
                            if (matkhau.trim().length() < 6 || matkhau.trim().length() > 36){
                                Toast.makeText(getActivity(), "Độ dài mật khẩu từ 6 -> 36 ký tự", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }, 3000);
            }
        });
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }
    private static final int setInterval() {
        if (interval == 1){
            timer.cancel();
        }
        return --interval;
    }

    public void checkUser(String us) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.checkusername(us);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "Tài khoản đã được sử dụng", Toast.LENGTH_SHORT).show();
                    } else {
                        accept = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }

        });
    }
    public void checkEmail(String em) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.checkemail(em);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "email đã được sử dụng", Toast.LENGTH_SHORT).show();
                    } else {
                        aceptmail = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }

        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ExampleDialogListener) context;
    }
    public interface ExampleDialogListener{
        void apply(String taikhoan, String matkhau, String email);
    }

}
