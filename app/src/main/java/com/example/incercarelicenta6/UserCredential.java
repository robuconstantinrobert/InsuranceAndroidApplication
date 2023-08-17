package com.example.incercarelicenta6;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.incercarelicenta6.UserInfo.ApiServiceUserInfo;
import com.example.incercarelicenta6.UserInfo.UserInfo;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserCredential extends AppCompatActivity {

    private List<UserInfo> objectUserInfoList;
    private Handler handler;

    private EditText nameEditText;
    private EditText addressEditText;
    private Button buttonCheckData;

    private Spinner spinnerInsuranceType;
    private Spinner vehicleType;
    private Spinner vehicleYear;
    private Spinner spinnerMethodType;
    private UserInfo reportUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_credential);

        nameEditText = findViewById(R.id.textInputEditTextName);
        addressEditText = findViewById(R.id.textInputEditTextAddress);
        buttonCheckData = findViewById(R.id.buttonCheckData);

        handler = new Handler();

        spinnerMethodType = findViewById(R.id.spinnerMethod);
        ArrayAdapter<String> insuranceMethodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        insuranceMethodAdapter.add("PHYD");
        insuranceMethodAdapter.add("PAYD");
        spinnerMethodType.setAdapter(insuranceMethodAdapter);

        spinnerInsuranceType = findViewById(R.id.spinner);
        ArrayAdapter<String> insuranceTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        insuranceTypeAdapter.add("Low coverage");
        insuranceTypeAdapter.add("Medium coverage");
        insuranceTypeAdapter.add("Full coverage");
        spinnerInsuranceType.setAdapter(insuranceTypeAdapter);

        vehicleType = findViewById(R.id.spinner2);
        ArrayAdapter<String> vehicleTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        vehicleTypeAdapter.add("Car");
        vehicleTypeAdapter.add("SUV");
        vehicleTypeAdapter.add("Business Car");
        vehicleType.setAdapter(vehicleTypeAdapter);

        // Populate the vehicle year spinner
        vehicleYear = findViewById(R.id.spinner3);
        ArrayAdapter<String> vehicleYearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        vehicleYearAdapter.add("2021");
        vehicleYearAdapter.add("2020");
        vehicleYearAdapter.add("2019");
        vehicleYear.setAdapter(vehicleYearAdapter);

        buttonCheckData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();

                boolean credentialsCorrect = checkUserCredentials(name, address);
                if (credentialsCorrect) {
                    Toast.makeText(UserCredential.this, "Credentials are correct", Toast.LENGTH_SHORT).show();
                    reportUser = getUserCredentials(name, address);
                } else {
                    Toast.makeText(UserCredential.this, "Credentials are incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonGenerateReport = findViewById(R.id.buttonGenerateReport);
        buttonGenerateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insuranceType = spinnerInsuranceType.getSelectedItem().toString();
                String selectedVehicleType = vehicleType.getSelectedItem().toString();
                String selectedVehicleYear = vehicleYear.getSelectedItem().toString();
                String selectedMethod = spinnerMethodType.getSelectedItem().toString();
                Intent intent = new Intent(UserCredential.this, ReportActivity.class);
                intent.putExtra("reportUser", reportUser);
                intent.putExtra("insuranceType", insuranceType);
                intent.putExtra("selectedVehicleType", selectedVehicleType);
                intent.putExtra("selectedVehicleYear", selectedVehicleYear);
                intent.putExtra("selectedMethod", selectedMethod);
                startActivity(intent);
            }
        });

        ImageButton buttonInfo = findViewById(R.id.imageButton);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCredential.this, MethodInfo.class);
                startActivity(intent);
            }
        });

        Button buttonBack = findViewById(R.id.button);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchUserInfoObjectListFromServer();
    }

    private void fetchUserInfoObjectListFromServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiServiceUserInfo apiService = retrofit.create(ApiServiceUserInfo.class);


        Call<List<UserInfo>> call = apiService.getObjectList();
        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    objectUserInfoList = response.body();
                } else {
                    Log.e("API Error", "Failed to fetch User info object list");
                }
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                Log.e("API Error", "Failed to fetch User info object list", t);
            }
        });
    }

    private boolean checkUserCredentials(String name, String address) {
        if (objectUserInfoList != null) {
            for (UserInfo userInfo : objectUserInfoList) {
                if (userInfo.getName().equals(name) && userInfo.getAdress().equals(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    private UserInfo getUserCredentials(String name, String address)
    {
        UserInfo user = null;
        if(objectUserInfoList != null)
        {
            for(UserInfo u : objectUserInfoList)
            {
                if(u.getName().equals(name) && u.getAdress().equals(address))
                {
                    user = u;
                }
            }
        }
        return user;
    }

}