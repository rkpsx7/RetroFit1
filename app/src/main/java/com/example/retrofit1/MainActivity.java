package com.example.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mEtUserId;
    private Button mBtnCallAPI;
    private TextView mTvFirstName,mTvLastName,mTvEmail;
    private ImageView mIvAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTvFirstName = findViewById(R.id.tvfirstName);
        mEtUserId = findViewById(R.id.etUserId);
        mTvLastName = findViewById(R.id.tvLastName);
        mBtnCallAPI = findViewById(R.id.btnCallApi);
        mTvEmail = findViewById(R.id.tvEmail);
        mIvAvatar = findViewById(R.id.ivAvatar);

        mBtnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = Network.getInstance().create(ApiService.class);
                int userId = Integer.parseInt(mEtUserId.getText().toString());
                apiService.getUser(userId).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        ResponseModel model = response.body();
                        String fname = model.getData().getFirstName();
                        String lname = model.getData().getLastName();
                        String email = model.getData().getEmail();
                        mTvFirstName.setText(fname);
                        mTvLastName.setText(lname);
                        mTvEmail.setText(email);
                        Glide.with(mIvAvatar).load(model.getData().getAvatar()).into(mIvAvatar);
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });
            }
        });

    }
}