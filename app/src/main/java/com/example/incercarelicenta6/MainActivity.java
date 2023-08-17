package com.example.incercarelicenta6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.incercarelicenta6.AbsBarPressure.AbsBarPressure;
import com.example.incercarelicenta6.AbsBarPressure.ApiServiceAbsBarPress;
import com.example.incercarelicenta6.BoostPressure.ApiServiceBoostPressure;
import com.example.incercarelicenta6.BoostPressure.BoostPressure;
import com.example.incercarelicenta6.CoolantTemp.ApiServiceCoolantTemp;
import com.example.incercarelicenta6.CoolantTemp.CoolantTemp;
import com.example.incercarelicenta6.EngineLoad.ApiServiceEngineLoad;
import com.example.incercarelicenta6.EngineLoad.EngineLoad;
import com.example.incercarelicenta6.FuelLevel.ApiServiceFuelLevel;
import com.example.incercarelicenta6.FuelLevel.FuelLevel;
import com.example.incercarelicenta6.IntakeTemp.ApiServiceIntakeTemp;
import com.example.incercarelicenta6.IntakeTemp.IntakeTemp;
import com.example.incercarelicenta6.O2Sensor.ApiServiceO2Sensor;
import com.example.incercarelicenta6.O2Sensor.O2Sensor;
import com.example.incercarelicenta6.Odometer.ApiServiceOdometer;
import com.example.incercarelicenta6.Odometer.Odometer;
import com.example.incercarelicenta6.OilTemp.ApiServiceOilTemp;
import com.example.incercarelicenta6.OilTemp.OilTemp;
import com.example.incercarelicenta6.RPM.ApiServiceRPM;
import com.example.incercarelicenta6.RPM.RPM;
import com.example.incercarelicenta6.Speed.ApiServiceSpeed;
import com.example.incercarelicenta6.Speed.Speed;
import com.example.incercarelicenta6.ThrottlePos.ApiServiceThrottlePos;
import com.example.incercarelicenta6.ThrottlePos.ThrottlePos;
import com.example.incercarelicenta6.TimingAdvance.ApiServiceTimingAdvance;
import com.example.incercarelicenta6.TimingAdvance.TimingAdvance;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView valueAbsPressureTextView;
    private TextView timestampAbsPressureTextView;

    private TextView valueRPMTextView;
    private TextView timestampRPMTextView;

    private TextView valueBoostPressureTextView;
    private TextView timestampBoostPressureTextView;

    private TextView valueCoolantTempTextView;
    private TextView timestampCoolantTempTextView;

    private TextView valueEngineLoadTextView;
    private TextView timestampEngineLoadTextView;

    private TextView valueFuelLevelTextView;
    private TextView timestampFuelLevelTextView;

    private TextView valueIntakeTempTextView;
    private TextView timestampIntakeTempTextView;

    private TextView valueO2SensorTextView;
    private TextView timestampO2SensorTextView;

    private TextView valueOdometerTextView;
    private TextView timestampOdometerTextView;

    private TextView valueOilTempTextView;
    private TextView timestampOilTempTextView;

    private TextView valueSpeedTextView;
    private TextView timestampSpeedTextView;

    private TextView valueThrottleTextView;
    private TextView timestampThrottleTextView;

    private TextView valueTimingTextView;
    private TextView timestampTimingTextView;

    private Timer timer;
    private List<AbsBarPressure> objectAbsBarPressureList;
    private List<RPM> objectRPMList;
    private List<BoostPressure> objectBoostPressureList;
    private List<CoolantTemp> objectCoolantTempList;
    private List<EngineLoad> objectEngineLoadList;
    private List<FuelLevel> objectFuelLevelList;
    private List<IntakeTemp> objectIntakeTempList;
    private List<O2Sensor> objectO2SensorList;
    private List<Odometer> objectOdometerList;
    private List<OilTemp> objectOilTempList;
    private List<Speed> objectSpeedList;
    private List<ThrottlePos> objectThrottleList;
    private List<TimingAdvance> objectTimingList;

    private int currentIndexAbsPress = 0;
    private int currentIndexRPM = 0;
    private int currentIndexBoostPressure = 0;
    private int currentIndexCoolantTemp = 0;
    private int currentIndexEngineLoad = 0;
    private int currentIndexFuelLevel = 0;
    private int currentIndexIntakeTemp = 0;
    private int currentIndexO2Sensor = 0;
    private int currentIndexOdometer = 0;
    private int currentIndexOilTemp = 0;
    private int currentIndexSpeed = 0;
    private int currentIndexThrottle = 0;
    private int currentIndexTiming = 0;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueAbsPressureTextView = findViewById(R.id.absoluteBarometricPressureValueTextView);
        timestampAbsPressureTextView = findViewById(R.id.timestampAbsoluteBarometricPressureTextView);

        valueRPMTextView = findViewById(R.id.RPMValueTextView);
        timestampRPMTextView = findViewById(R.id.timestampRPMTextView);

        valueBoostPressureTextView = findViewById(R.id.BoostPressureValueTextView);
        timestampBoostPressureTextView = findViewById(R.id.timestampBoostPressureTextView);

        valueCoolantTempTextView = findViewById(R.id.CoolantTempValueTextView);
        timestampCoolantTempTextView = findViewById(R.id.timestampCoolantTemperatureTextView);

        valueEngineLoadTextView = findViewById(R.id.EngineLoadValueTextView);
        timestampEngineLoadTextView = findViewById(R.id.timestampEngineLoadTextView);

        valueFuelLevelTextView = findViewById(R.id.FuelLevelValueTextView);
        timestampFuelLevelTextView = findViewById(R.id.timestampFuelLevelTextView);

        valueIntakeTempTextView = findViewById(R.id.IntakeTempValueTextView);
        timestampIntakeTempTextView = findViewById(R.id.timestampIntakeTempTextView);

        valueO2SensorTextView = findViewById(R.id.O2SensorValueTextView);
        timestampO2SensorTextView = findViewById(R.id.timestampO2SensorTextView);

        valueOdometerTextView = findViewById(R.id.OdometerValueTextView);
        timestampOdometerTextView = findViewById(R.id.timestampOdometerTextView);

        valueOilTempTextView = findViewById(R.id.OilTempValueTextView);
        timestampOilTempTextView = findViewById(R.id.timestampOilTempTextView);

        valueSpeedTextView = findViewById(R.id.SpeedValueTextView);
        timestampSpeedTextView = findViewById(R.id.timestampSpeedTextView);

        valueThrottleTextView = findViewById(R.id.ThrottleValueTextView);
        timestampThrottleTextView = findViewById(R.id.timestampThrottleTextView);

        valueTimingTextView = findViewById(R.id.TimingValueTextView);
        timestampTimingTextView = findViewById(R.id.timestampTimingTextView);




        handler = new Handler();

        // Start the timer to update the parameter values
        timer = new Timer();
        timer.schedule(new UpdateTask(), 0, 2000); // Update every 2 seconds

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
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private class UpdateTask extends TimerTask {
        @Override
        public void run() {
            // Fetch the object lists from the Spring Boot server
            objectAbsBarPressureList = fetchAbsBarPressureObjectListFromServer();
            objectRPMList = fetchRPMObjectListFromServer();
            objectBoostPressureList = fetchBoostPressureObjectListFromServer();
            objectCoolantTempList = fetchCoolantTempObjectListFromServer();
            objectEngineLoadList = fetchEngineLoadObjectListFromServer();
            objectFuelLevelList = fetchFuelLevelObjectListFromServer();
            objectIntakeTempList = fetchIntakeTempObjectListFromServer();
            objectO2SensorList = fetchO2SensorObjectListFromServer();
            objectOdometerList = fetchOdometerObjectListFromServer();
            objectOilTempList = fetchOilTempObjectListFromServer();
            objectSpeedList = fetchSpeedObjectListFromServer();
            objectThrottleList = fetchThrottleObjectListFromServer();
            objectTimingList = fetchTimingObjectListFromServer();

            // Check if any of the object lists is empty or null
            if (objectAbsBarPressureList == null || objectAbsBarPressureList.isEmpty() ||
                    objectRPMList == null || objectRPMList.isEmpty() ||
            objectBoostPressureList == null || objectBoostPressureList.isEmpty() ||
            objectCoolantTempList == null || objectCoolantTempList.isEmpty() ||
            objectEngineLoadList == null || objectEngineLoadList.isEmpty() ||
            objectFuelLevelList == null || objectFuelLevelList.isEmpty() ||
            objectIntakeTempList == null || objectIntakeTempList.isEmpty() ||
            objectO2SensorList == null || objectO2SensorList.isEmpty() ||
            objectOdometerList == null || objectOdometerList.isEmpty() ||
            objectOilTempList == null || objectOilTempList.isEmpty() ||
            objectSpeedList == null || objectSpeedList.isEmpty() ||
            objectThrottleList == null || objectThrottleList.isEmpty() ||
            objectTimingList == null || objectTimingList.isEmpty()) {
                Log.e("API Error", "Empty or null object list received");
                return;
            }

            // Check if the current index for AbsBarPressure is within the list size
            if (currentIndexAbsPress >= objectAbsBarPressureList.size()) {
                // Reset the index to 0 if it exceeds the list size
                currentIndexAbsPress = 0;
            }

            // Check if the current index for RPM is within the list size
            if (currentIndexRPM >= objectRPMList.size()) {
                // Reset the index to 0 if it exceeds the list size
                currentIndexRPM = 0;
            }

            if(currentIndexBoostPressure >= objectBoostPressureList.size())
            {
                currentIndexBoostPressure = 0;
            }

            if(currentIndexCoolantTemp >= objectCoolantTempList.size())
            {
                currentIndexCoolantTemp = 0;
            }

            if(currentIndexEngineLoad >= objectEngineLoadList.size())
            {
                currentIndexEngineLoad = 0;
            }

            if(currentIndexFuelLevel >= objectFuelLevelList.size())
            {
                currentIndexFuelLevel = 0;
            }

            if(currentIndexIntakeTemp >= objectIntakeTempList.size())
            {
                currentIndexIntakeTemp = 0;
            }

            if(currentIndexO2Sensor >= objectO2SensorList.size())
            {
                currentIndexO2Sensor = 0;
            }

            if(currentIndexOdometer >= objectOdometerList.size())
            {
                currentIndexOdometer = 0;
            }

            if(currentIndexOilTemp >= objectOilTempList.size())
            {
                currentIndexOilTemp = 0;
            }

            if(currentIndexSpeed >= objectSpeedList.size())
            {
                currentIndexSpeed = 0;
            }

            if(currentIndexThrottle >= objectThrottleList.size())
            {
                currentIndexThrottle = 0;
            }

            if(currentIndexTiming >= objectTimingList.size())
            {
                currentIndexTiming = 0;
            }

            // Get the current objects from the lists
            AbsBarPressure currentObjectAbsPress = objectAbsBarPressureList.get(currentIndexAbsPress);
            RPM currentObjectRPM = objectRPMList.get(currentIndexRPM);
            BoostPressure currentObjectBoostPressure = objectBoostPressureList.get(currentIndexBoostPressure);
            CoolantTemp currentObjectCoolantTemp = objectCoolantTempList.get(currentIndexCoolantTemp);
            EngineLoad currentObjectEngineLoad = objectEngineLoadList.get(currentIndexEngineLoad);
            FuelLevel currentObjectFuelLevel = objectFuelLevelList.get(currentIndexFuelLevel);
            IntakeTemp currentObjectIntakeTemp = objectIntakeTempList.get(currentIndexIntakeTemp);
            O2Sensor currentObjectO2Sensor = objectO2SensorList.get(currentIndexO2Sensor);
            Odometer currentObjectOdometer = objectOdometerList.get(currentIndexOdometer);
            OilTemp currentObjectOilTemp = objectOilTempList.get(currentIndexOilTemp);
            Speed currentObjectSpeed = objectSpeedList.get(currentIndexSpeed);
            ThrottlePos currentObjectThrottle = objectThrottleList.get(currentIndexThrottle);
            TimingAdvance currentObjectTiming = objectTimingList.get(currentIndexTiming);

            // Get the current values and timestamps
            int valueAbsPress = currentObjectAbsPress.getAbsBarPressureValue();
            String timestampAbsPress = currentObjectAbsPress.getTimestamp();

            int valueRPM = currentObjectRPM.getRpmvalue();
            String timestampRPM = currentObjectRPM.getTimestamp();

            int valueBoostPressure = currentObjectBoostPressure.getBoostValue();
            String timestampBoostPressure = currentObjectBoostPressure.getTimestamp();

            int valueCoolantTemp = currentObjectCoolantTemp.getCoolantTempValue();
            String timestampCoolantTemp = currentObjectCoolantTemp.getTimestamp();

            int valueEngineLoad = currentObjectEngineLoad.getLoadValue();
            String timestampEngineLoad = currentObjectEngineLoad.getTimestamp();

            int valueFuelLevel = currentObjectFuelLevel.getFuelValue();
            String timestampFuelLevel = currentObjectFuelLevel.getTimestamp();

            int valueIntakeTemp = currentObjectIntakeTemp.getIntakeValue();
            String timestampIntakeTemp = currentObjectIntakeTemp.getTimestamp();

            int valueO2Sensor = currentObjectO2Sensor.getO2SensorValue();
            String timestampO2Sensor = currentObjectO2Sensor.getTimestamp();

            int valueOdometer = currentObjectOdometer.getOdometerValue();
            String timestampOdometer = currentObjectOdometer.getTimestamp();

            int valueOilTemp = currentObjectOilTemp.getOilTempValue();
            String timestampOilTemp = currentObjectOilTemp.getTimestamp();

            int valueSpeed = currentObjectSpeed.getSpeedValue();
            String timestampSpeed = currentObjectSpeed.getTimestamp();

            int valueThrottle = currentObjectThrottle.getThrottleValue();
            String timestampThrottle = currentObjectThrottle.getTimestamp();

            int valueTiming = currentObjectTiming.getTimingValue();
            String timestampTiming = currentObjectTiming.getTimestamp();

            // Update the value and timestamp TextViews
            handler.post(new Runnable() {
                @Override
                public void run() {
                    valueAbsPressureTextView.setText(String.valueOf(valueAbsPress));
                    timestampAbsPressureTextView.setText(timestampAbsPress);

                    valueRPMTextView.setText(String.valueOf(valueRPM));
                    timestampRPMTextView.setText(timestampRPM);

                    valueBoostPressureTextView.setText(String.valueOf(valueBoostPressure));
                    timestampBoostPressureTextView.setText(timestampBoostPressure);

                    valueCoolantTempTextView.setText(String.valueOf(valueCoolantTemp));
                    timestampCoolantTempTextView.setText(timestampCoolantTemp);

                    valueEngineLoadTextView.setText(String.valueOf(valueEngineLoad));
                    timestampEngineLoadTextView.setText(timestampEngineLoad);

                    valueFuelLevelTextView.setText(String.valueOf(valueFuelLevel));
                    timestampFuelLevelTextView.setText(timestampFuelLevel);

                    valueIntakeTempTextView.setText(String.valueOf(valueIntakeTemp));
                    timestampIntakeTempTextView.setText(timestampIntakeTemp);

                    valueO2SensorTextView.setText(String.valueOf(valueO2Sensor));
                    timestampO2SensorTextView.setText(timestampO2Sensor);

                    valueOdometerTextView.setText(String.valueOf(valueOdometer));
                    timestampOdometerTextView.setText(timestampOdometer);

                    valueOilTempTextView.setText(String.valueOf(valueOilTemp));
                    timestampOilTempTextView.setText(timestampOilTemp);

                    valueSpeedTextView.setText(String.valueOf(valueSpeed));
                    timestampSpeedTextView.setText(timestampSpeed);

                    valueThrottleTextView.setText(String.valueOf(valueThrottle));
                    timestampThrottleTextView.setText(timestampThrottle);

                    valueTimingTextView.setText(String.valueOf(valueTiming));
                    timestampTimingTextView.setText(String.valueOf(timestampTiming));
                }
            });

            // Increment the current indices
            currentIndexAbsPress++;
            currentIndexRPM++;
            currentIndexBoostPressure++;
            currentIndexCoolantTemp++;
            currentIndexEngineLoad++;
            currentIndexFuelLevel++;
            currentIndexIntakeTemp++;
            currentIndexO2Sensor++;
            currentIndexOdometer++;
            currentIndexOilTemp++;
            currentIndexSpeed++;
            currentIndexThrottle++;
            currentIndexTiming++;
        }
    }

    private List<AbsBarPressure> fetchAbsBarPressureObjectListFromServer() {
        List<AbsBarPressure> objectAbsPressList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceAbsBarPress apiService = retrofit.create(ApiServiceAbsBarPress.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<AbsBarPressure>> call = apiService.getObjectList();
        try {
            Response<List<AbsBarPressure>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectAbsPressList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch AbsBarPressure object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectAbsPressList;
    }

    private List<BoostPressure> fetchBoostPressureObjectListFromServer() {
        List<BoostPressure> objectBoostPressureList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceBoostPressure apiService = retrofit.create(ApiServiceBoostPressure.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<BoostPressure>> call = apiService.getObjectList();
        try {
            Response<List<BoostPressure>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectBoostPressureList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch BoostPressure object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectBoostPressureList;
    }

    private List<RPM> fetchRPMObjectListFromServer() {
        List<RPM> objectRPMList = null;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiServiceRPM apiService = retrofit.create(ApiServiceRPM.class);


        Call<List<RPM>> call = apiService.getObjectList();
        try {
            Response<List<RPM>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectRPMList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch RPM object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectRPMList;
    }

    private List<CoolantTemp> fetchCoolantTempObjectListFromServer() {
        List<CoolantTemp> objectCoolantTempList1 = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceCoolantTemp apiService = retrofit.create(ApiServiceCoolantTemp.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<CoolantTemp>> call = apiService.getObjectList();
        try {
            Response<List<CoolantTemp>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectCoolantTempList1 = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Coolant Temperature object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectCoolantTempList1;
    }


    private List<EngineLoad> fetchEngineLoadObjectListFromServer() {
        List<EngineLoad> objectEngineLoadList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceEngineLoad apiService = retrofit.create(ApiServiceEngineLoad.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<EngineLoad>> call = apiService.getObjectList();
        try {
            Response<List<EngineLoad>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectEngineLoadList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Engine Load object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectEngineLoadList;
    }

    private List<FuelLevel> fetchFuelLevelObjectListFromServer() {
        List<FuelLevel> objectFuelLevelList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceFuelLevel apiService = retrofit.create(ApiServiceFuelLevel.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<FuelLevel>> call = apiService.getObjectList();
        try {
            Response<List<FuelLevel>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectFuelLevelList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Fuel Level object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectFuelLevelList;
    }

    private List<IntakeTemp> fetchIntakeTempObjectListFromServer() {
        List<IntakeTemp> objectIntakeTempList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceIntakeTemp apiService = retrofit.create(ApiServiceIntakeTemp.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<IntakeTemp>> call = apiService.getObjectList();
        try {
            Response<List<IntakeTemp>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectIntakeTempList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Intake Temperature object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectIntakeTempList;
    }

    private List<O2Sensor> fetchO2SensorObjectListFromServer() {
        List<O2Sensor> objectO2SensorList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceO2Sensor apiService = retrofit.create(ApiServiceO2Sensor.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<O2Sensor>> call = apiService.getObjectList();
        try {
            Response<List<O2Sensor>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectO2SensorList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch O2 Sensor object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectO2SensorList;
    }

    private List<Odometer> fetchOdometerObjectListFromServer() {
        List<Odometer> objectOdometerList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceOdometer apiService = retrofit.create(ApiServiceOdometer.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<Odometer>> call = apiService.getObjectList();
        try {
            Response<List<Odometer>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectOdometerList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Odometer object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectOdometerList;
    }

    private List<OilTemp> fetchOilTempObjectListFromServer() {
        List<OilTemp> objectOilTempList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceOilTemp apiService = retrofit.create(ApiServiceOilTemp.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<OilTemp>> call = apiService.getObjectList();
        try {
            Response<List<OilTemp>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectOilTempList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Oil temperature object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectOilTempList;
    }

    private List<Speed> fetchSpeedObjectListFromServer() {
        List<Speed> objectSpeedList = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceSpeed apiService = retrofit.create(ApiServiceSpeed.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<Speed>> call = apiService.getObjectList();
        try {
            Response<List<Speed>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectSpeedList = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Speed object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectSpeedList;
    }

    private List<ThrottlePos> fetchThrottleObjectListFromServer() {
        List<ThrottlePos> objectThrottlePos = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceThrottlePos apiService = retrofit.create(ApiServiceThrottlePos.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<ThrottlePos>> call = apiService.getObjectList();
        try {
            Response<List<ThrottlePos>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectThrottlePos = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Throttle position object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectThrottlePos;
    }

    private List<TimingAdvance> fetchTimingObjectListFromServer() {
        List<TimingAdvance> objectTimingAdvance = null;

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.179.112:5020")  // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an API service using the Retrofit instance
        ApiServiceTimingAdvance apiService = retrofit.create(ApiServiceTimingAdvance.class);  // Replace with your API service interface

        // Make the API call to fetch the object list
        Call<List<TimingAdvance>> call = apiService.getObjectList();
        try {
            Response<List<TimingAdvance>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                objectTimingAdvance = response.body();
            } else {
                Log.e("API Error", "Failed to fetch Timing Advance object list");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectTimingAdvance;
    }

}