package com.example.incercarelicenta6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.incercarelicenta6.Circle.CircularProgressBar;
import com.example.incercarelicenta6.Odometer.ApiServiceOdometer;
import com.example.incercarelicenta6.Odometer.Odometer;
import com.example.incercarelicenta6.RPM.ApiServiceRPM;
import com.example.incercarelicenta6.RPM.RPM;
import com.example.incercarelicenta6.Speed.ApiServiceSpeed;
import com.example.incercarelicenta6.Speed.Speed;
import com.example.incercarelicenta6.UserInfo.ApiServiceUserInfo;
import com.example.incercarelicenta6.UserInfo.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportActivity extends AppCompatActivity {

    private List<UserInfo> objectUserInfoList;
    private List<Speed> speedList;
    private List<RPM> rpmList;
    private List<Odometer> odometerList;

    private UserInfo returnedUser;

    private String insuranceType;
    private String vehicleType;
    private String vehicleYear;
    private String insuranceMethod;

    private TextView nameTextViewValue;
    private TextView addressTextViewValue;
    private TextView insuranceTypeValue;
    private TextView vehicleTypeValue;
    private TextView vehicleYearValue;
    private TextView insurancePriceValue;
    private TextView drivingStyleValue;
    private TextView insuranceMethodValue;
    private ProgressBar progressBarDrivingStyleValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        returnedUser = getIntent().getParcelableExtra("reportUser");

        insuranceType = getIntent().getStringExtra("insuranceType");
        vehicleType = getIntent().getStringExtra("selectedVehicleType");
        vehicleYear = getIntent().getStringExtra("selectedVehicleYear");
        insuranceMethod = getIntent().getStringExtra("selectedMethod");
        nameTextViewValue = findViewById(R.id.textViewValueName);
        addressTextViewValue = findViewById(R.id.textViewValueAddress);
        insuranceTypeValue = findViewById(R.id.textViewValueInsuranceType);
        vehicleTypeValue = findViewById(R.id.textViewValueCarType);
        vehicleYearValue = findViewById(R.id.textViewValueCarYear);
        insurancePriceValue = findViewById(R.id.textViewValueInsurancePrice);
        drivingStyleValue = findViewById(R.id.textViewValueDrivingStyle);
        progressBarDrivingStyleValue = findViewById(R.id.progressBar);
        insuranceMethodValue = findViewById(R.id.textViewMethodValue);

        String nameText = returnedUser.getName();
        String addressText = returnedUser.getAdress();

        fetchUserInfoFromServer();
        fetchSpeedFromServer();
        fetchRPMFromServer();
        fetchOdometerFromServer();


        Button buttonBack = findViewById(R.id.button3);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, UserCredential.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void fetchUserInfoFromServer() {
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
                    processReport();
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

    private void fetchSpeedFromServer() {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceSpeed apiService = retrofit.create(ApiServiceSpeed.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<Speed>> call = apiService.getObjectList();
        call.enqueue(new Callback<List<Speed>>() {
            @Override
            public void onResponse(Call<List<Speed>> call, Response<List<Speed>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    speedList = response.body();
                    processReport();
                } else {
                    Log.e("API Error", "Failed to fetch speed object list");
                }
            }

            @Override
            public void onFailure(Call<List<Speed>> call, Throwable t) {
                Log.e("API Error", "Failed to fetch speed object list", t);
            }
        });
    }

    private void fetchRPMFromServer() {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceRPM apiService = retrofit.create(ApiServiceRPM.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<RPM>> call = apiService.getObjectList();
        call.enqueue(new Callback<List<RPM>>() {
            @Override
            public void onResponse(Call<List<RPM>> call, Response<List<RPM>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    rpmList = response.body();
                    processReport();
                } else {
                    Log.e("API Error", "Failed to fetch rpm object list");
                }
            }

            @Override
            public void onFailure(Call<List<RPM>> call, Throwable t) {
                Log.e("API Error", "Failed to fetch rpm object list", t);
            }
        });
    }

    private void fetchOdometerFromServer() {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceOdometer apiService = retrofit.create(ApiServiceOdometer.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<Odometer>> call = apiService.getObjectList();
        call.enqueue(new Callback<List<Odometer>>() {
            @Override
            public void onResponse(Call<List<Odometer>> call, Response<List<Odometer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    odometerList = response.body();
                    processReport();
                } else {
                    Log.e("API Error", "Failed to fetch odometer object list");
                }
            }

            @Override
            public void onFailure(Call<List<Odometer>> call, Throwable t) {
                Log.e("API Error", "Failed to fetch odometer object list", t);
            }
        });
    }

    private int calculateDrivingExperience(UserInfo tempUser) {
        try {
            String returnedDate = tempUser.getLicense();
            Log.d("Date", "Returned Date: " + returnedDate);
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            Date givenDate = null;
            givenDate = sdf.parse(returnedDate);
            calendar.setTime(givenDate);
            int givenYear = calendar.get(Calendar.YEAR);

            int drivingExperience = currentYear - givenYear;
            return drivingExperience;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private double calculateAvgRPM(List<RPM> list)
    {
        double avgRpm = 0;
        for(RPM r : list)
        {
            avgRpm += r.getRpmvalue();
        }
        return avgRpm = avgRpm / list.size();
    }

    private double calculateAvgSpeed(List<Speed> list)
    {
        double avgSpeed = 0;
        for(Speed s : list)
        {
            avgSpeed += s.getSpeedValue();
        }
        return avgSpeed = avgSpeed / list.size();
    }

    private void processReport() {
        String method = insuranceMethod;
        if("PHYD".equals(insuranceMethod)) {
            if (speedList != null && rpmList != null) {
                double baseInsurance = 1000.0;
                int insurancePoints = 100;
                int drivingExperience = calculateDrivingExperience(returnedUser);

                if (drivingExperience <= 5) {
                    baseInsurance += 500;
                } else if (drivingExperience > 5 && drivingExperience <= 10) {
                    baseInsurance += 250;
                } else if (drivingExperience > 10 && drivingExperience <= 15) {
                    baseInsurance += 50;
                } else if (drivingExperience > 15 && drivingExperience <= 25) {
                    baseInsurance -= 250;
                } else if (drivingExperience > 25) {
                    baseInsurance -= 500;
                }

                int tickets = returnedUser.getTickets();
                if (tickets > 0 && tickets <= 2) {
                    baseInsurance += 100;
                    insurancePoints -= 10;
                } else if (tickets > 2 && tickets <= 6) {
                    baseInsurance += 300;
                    insurancePoints -= 20;
                } else if (tickets > 6 && tickets < 10) {
                    baseInsurance += 500;
                    insurancePoints -= 30;
                } else if (tickets >= 10) {
                    baseInsurance += 700;
                    insurancePoints -= 50;
                } else if (tickets == 0) {
                    baseInsurance -= 300;
                    insurancePoints += 0;
                }

                int suspension30 = returnedUser.getSuspensionThirty();
                int suspension60 = returnedUser.getSuspensionSixty();
                int suspension90 = returnedUser.getSuspensionNinety();

                if (suspension30 == 0 && suspension60 == 0 && suspension90 == 0) {
                    baseInsurance -= 300;
                    insurancePoints += 0;
                } else if (suspension30 == 1 && suspension60 == 0 && suspension90 == 0) {
                    baseInsurance += 100;
                    insurancePoints -= 10;
                } else if (suspension30 == 0 && suspension60 == 1 && suspension90 == 0) {
                    baseInsurance += 200;
                    insurancePoints -= 20;
                } else if (suspension30 == 0 && suspension60 == 0 && suspension90 == 1) {
                    baseInsurance += 300;
                    insurancePoints -= 30;
                } else if (suspension30 == 1 && suspension60 == 1 && suspension90 == 0) {
                    baseInsurance += 400;
                    insurancePoints -= 30;
                } else if (suspension30 == 0 && suspension60 == 1 && suspension90 == 1) {
                    baseInsurance += 600;
                    insurancePoints -= 60;
                } else if (suspension30 == 1 && suspension60 == 0 && suspension90 == 1) {
                    baseInsurance += 500;
                    insurancePoints -= 50;
                } else if (suspension30 == 1 && suspension60 == 1 && suspension90 == 1) {
                    baseInsurance += 700;
                    insurancePoints -= 70;
                }

                String insurance = insuranceType;
                if (insurance.equals("Low coverage")) {
                    baseInsurance += 0;
                } else if (insurance.equals("Medium coverage")) {
                    baseInsurance += 500;
                } else if (insurance.equals("Full coverage")) {
                    baseInsurance += 1000;
                }

                String vehicle = vehicleType;
                if (vehicle.equals("Car")) {
                    baseInsurance += 100;
                } else if (vehicle.equals("SUV")) {
                    baseInsurance += 400;
                } else if (vehicle.equals("Business Car")) {
                    baseInsurance += 500;
                }

                int yearVehicle = Integer.parseInt(vehicleYear);
                if (yearVehicle >= 2020 && yearVehicle <= 2023) {
                    baseInsurance += 500;
                } else if (yearVehicle < 2020 && yearVehicle >= 2015) {
                    baseInsurance += 350;
                } else if (yearVehicle < 2015 && yearVehicle >= 2010) {
                    baseInsurance += 250;
                } else if (yearVehicle < 2010) {
                    baseInsurance += 100;
                }

                double averageSpeed = calculateAvgSpeed(speedList);
                if (averageSpeed <= 25) {
                    baseInsurance -= 300;
                    insurancePoints += 0;
                } else if (averageSpeed > 25 && averageSpeed <= 60) {
                    baseInsurance -= 200;
                    insurancePoints += 0;
                } else if (averageSpeed > 60 && averageSpeed < 130) {
                    baseInsurance += 300;
                    insurancePoints -= 30;
                } else if (averageSpeed > 130) {
                    baseInsurance += 500;
                    insurancePoints -= 50;
                }

                double averageRPM = calculateAvgRPM(rpmList);
                if (averageRPM < 2000) {
                    baseInsurance -= 400;
                    insurancePoints += 0;
                } else if (averageRPM > 2000 && averageRPM < 3000) {
                    baseInsurance -= 100;
                    insurancePoints += 0;
                } else if (averageRPM > 3000 && averageRPM < 4000) {
                    baseInsurance += 300;
                    insurancePoints -= 30;
                } else if (averageRPM > 4000) {
                    baseInsurance += 500;
                    insurancePoints -= 50;
                }

                nameTextViewValue.setText(returnedUser.getName());
                addressTextViewValue.setText(returnedUser.getAdress());
                insuranceTypeValue.setText(insuranceType);
                vehicleTypeValue.setText(vehicleType);
                vehicleYearValue.setText(vehicleYear);
                insurancePriceValue.setText(String.valueOf(baseInsurance));

                double tresholdRpm = 1000;
                int consecutiveCountRpm = 3;
                int countSpikesRpm = countSpikesRPM(rpmList, tresholdRpm, consecutiveCountRpm);

                double tresholdSpeed = 30;
                int consecutiveCountSpeed = 3;
                int countSpikesSpeed = countSpikesSpeed(speedList, tresholdSpeed, consecutiveCountSpeed);

                if (countSpikesRpm == 0) {
                    insurancePoints += 0;
                } else if (countSpikesRpm > 0 && countSpikesRpm < 2) {
                    insurancePoints -= 30;
                } else if (countSpikesRpm > 3) {
                    insurancePoints -= 50;
                }

                if (countSpikesSpeed == 0) {
                    insurancePoints += 0;
                } else if (countSpikesSpeed > 0 && countSpikesSpeed < 2) {
                    insurancePoints -= 30;
                } else if (countSpikesSpeed > 3) {
                    insurancePoints -= 50;
                }


                if (insurancePoints > 90 && insurancePoints <= 100) {
                    drivingStyleValue.setText("Exceptional driving");
                } else if (insurancePoints > 65 || insurancePoints < 90) {
                    drivingStyleValue.setText("Good driving");
                } else if (insurancePoints < 65) {
                    drivingStyleValue.setText("Aggressive driving");
                }

                displaySpikesInData(rpmList, speedList);
                TextView progressTextView = findViewById(R.id.textViewProgress);
                insuranceMethodValue.setText("PHYD");
                progressTextView.setText(String.valueOf(insurancePoints));
                progressBarDrivingStyleValue.setProgress(insurancePoints);
            }
        }
        else if("PAYD".equals(insuranceMethod))
        {
            if (odometerList != null) {
                double baseInsurance = 1000.0;
                int insurancePoints = 100;
                int drivingExperience = calculateDrivingExperience(returnedUser);

                if (drivingExperience <= 5) {
                    baseInsurance += 500;
                } else if (drivingExperience > 5 && drivingExperience <= 10) {
                    baseInsurance += 250;
                } else if (drivingExperience > 10 && drivingExperience <= 15) {
                    baseInsurance += 50;
                } else if (drivingExperience > 15 && drivingExperience <= 25) {
                    baseInsurance -= 250;
                } else if (drivingExperience > 25) {
                    baseInsurance -= 500;
                }

                int tickets = returnedUser.getTickets();
                if (tickets > 0 && tickets <= 2) {
                    baseInsurance += 100;
                    insurancePoints -= 10;
                } else if (tickets > 2 && tickets <= 6) {
                    baseInsurance += 300;
                    insurancePoints -= 20;
                } else if (tickets > 6 && tickets < 10) {
                    baseInsurance += 500;
                    insurancePoints -= 30;
                } else if (tickets >= 10) {
                    baseInsurance += 700;
                    insurancePoints -= 50;
                } else if (tickets == 0) {
                    baseInsurance -= 300;
                    insurancePoints += 0;
                }

                int suspension30 = returnedUser.getSuspensionThirty();
                int suspension60 = returnedUser.getSuspensionSixty();
                int suspension90 = returnedUser.getSuspensionNinety();

                if (suspension30 == 0 && suspension60 == 0 && suspension90 == 0) {
                    baseInsurance -= 300;
                    insurancePoints += 0;
                } else if (suspension30 == 1 && suspension60 == 0 && suspension90 == 0) {
                    baseInsurance += 100;
                    insurancePoints -= 10;
                } else if (suspension30 == 0 && suspension60 == 1 && suspension90 == 0) {
                    baseInsurance += 200;
                    insurancePoints -= 20;
                } else if (suspension30 == 0 && suspension60 == 0 && suspension90 == 1) {
                    baseInsurance += 300;
                    insurancePoints -= 30;
                } else if (suspension30 == 1 && suspension60 == 1 && suspension90 == 0) {
                    baseInsurance += 400;
                    insurancePoints -= 30;
                } else if (suspension30 == 0 && suspension60 == 1 && suspension90 == 1) {
                    baseInsurance += 600;
                    insurancePoints -= 60;
                } else if (suspension30 == 1 && suspension60 == 0 && suspension90 == 1) {
                    baseInsurance += 500;
                    insurancePoints -= 50;
                } else if (suspension30 == 1 && suspension60 == 1 && suspension90 == 1) {
                    baseInsurance += 700;
                    insurancePoints -= 70;
                }

                String insurance = insuranceType;
                if (insurance.equals("Low coverage")) {
                    baseInsurance += 0;
                } else if (insurance.equals("Medium coverage")) {
                    baseInsurance += 500;
                } else if (insurance.equals("Full coverage")) {
                    baseInsurance += 1000;
                }

                String vehicle = vehicleType;
                if (vehicle.equals("Car")) {
                    baseInsurance += 100;
                } else if (vehicle.equals("SUV")) {
                    baseInsurance += 400;
                } else if (vehicle.equals("Business Car")) {
                    baseInsurance += 500;
                }

                int yearVehicle = Integer.parseInt(vehicleYear);
                if (yearVehicle >= 2020 && yearVehicle <= 2023) {
                    baseInsurance += 500;
                } else if (yearVehicle < 2020 && yearVehicle >= 2015) {
                    baseInsurance += 350;
                } else if (yearVehicle < 2015 && yearVehicle >= 2010) {
                    baseInsurance += 250;
                } else if (yearVehicle < 2010) {
                    baseInsurance += 100;
                }

                Odometer firstOdometerIndex = odometerList.get(0);
                Odometer lastOdometerIndex = odometerList.get(odometerList.size() - 1);

                double firstOdometerValue = firstOdometerIndex.getOdometerValue();
                double lastOdometerValue = lastOdometerIndex.getOdometerValue();
                double distanceDriven = lastOdometerValue - firstOdometerValue;
                if(distanceDriven == 0)
                {
                    baseInsurance -= 500;
                    insurancePoints += 50;
                }
                else if(distanceDriven > 0 && distanceDriven < 100)
                {
                    baseInsurance -= 300;
                    insurancePoints += 30;
                }
                else if(distanceDriven > 100 && distanceDriven < 200)
                {
                    baseInsurance += 100;
                    insurancePoints -= 10;
                }
                else if(distanceDriven > 200 && distanceDriven < 500)
                {
                    baseInsurance += 200;
                    insurancePoints -= 20;
                }
                else if(distanceDriven > 500 && distanceDriven < 1000)
                {
                    baseInsurance += 300;
                    insurancePoints -= 30;
                }
                else if(distanceDriven > 1000 && distanceDriven < 2000)
                {
                    baseInsurance += 400;
                    insurancePoints -= 40;
                }
                else if(distanceDriven > 2000)
                {
                    baseInsurance += 700;
                    insurancePoints -= 55;
                }

                nameTextViewValue.setText(returnedUser.getName());
                addressTextViewValue.setText(returnedUser.getAdress());
                insuranceTypeValue.setText(insuranceType);
                vehicleTypeValue.setText(vehicleType);
                vehicleYearValue.setText(vehicleYear);
                insurancePriceValue.setText(String.valueOf(baseInsurance));

                if (insurancePoints > 90 && insurancePoints <= 100) {
                    drivingStyleValue.setText("Exceptional driving");
                } else if (insurancePoints > 65 || insurancePoints < 90) {
                    drivingStyleValue.setText("Good driving");
                } else if (insurancePoints < 65) {
                    drivingStyleValue.setText("Aggressive driving");
                }

                displaySpikesInData(rpmList, speedList);
                TextView progressTextView = findViewById(R.id.textViewProgress);
                insuranceMethodValue.setText("PAYD");
                progressTextView.setText(String.valueOf(insurancePoints));
                progressBarDrivingStyleValue.setProgress(insurancePoints);
            }
        }
    }

    private void displaySpikesInData(List<RPM> listRPM, List<Speed> listSpeed) {
        int rpmSpikes = countSpikesRPM(listRPM, 1000, 3);
        int speedSpikes = countSpikesSpeed(listSpeed, 30, 3);

        if (rpmSpikes >= 3) {
            Toast.makeText(this, "You are driving aggressively check your (RPM)", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "You are driving good", Toast.LENGTH_SHORT).show();
        }

        if (speedSpikes >= 3) {
            Toast.makeText(this, "You are driving aggressively check your (Speed)", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "You are driving good", Toast.LENGTH_SHORT).show();
        }
    }

    private int countSpikesRPM(List<RPM> dataList, double threshold, int consecutiveCount) {
        int spikes = 0;
        int consecutive = 0;

        for (int i = 0; i < dataList.size() - 1; i++) {
            RPM current = dataList.get(i);
            RPM next = dataList.get(i + 1);

            if (current.getRpmvalue() > threshold && next.getRpmvalue() <= threshold) {
                consecutive++;
            } else {
                consecutive = 0;
            }

            if (consecutive >= consecutiveCount) {
                spikes++;
                consecutive = 0; // Reset consecutive count
            }
        }

        return spikes;
    }

    private int countSpikesSpeed(List<Speed> dataList, double threshold, int consecutiveCount) {
        int spikes = 0;
        int consecutive = 0;

        for (int i = 0; i < dataList.size() - 1; i++) {
            Speed current = dataList.get(i);
            Speed next = dataList.get(i + 1);

            if (current.getSpeedValue() > threshold && next.getSpeedValue() <= threshold) {
                consecutive++;
            } else {
                consecutive = 0;
            }

            if (consecutive >= consecutiveCount) {
                spikes++;
                consecutive = 0;
            }
        }

        return spikes;
    }
}