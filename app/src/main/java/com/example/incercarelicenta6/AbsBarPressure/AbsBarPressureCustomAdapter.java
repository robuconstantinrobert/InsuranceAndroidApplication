//package com.example.incercarelicenta6.AbsBarPressure;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.incercarelicenta6.R;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//
//public class AbsBarPressureCustomAdapter extends ArrayAdapter<AbsBarPressure> {
//    private static class ViewHolder {
//        TextView valueTextView;
//        TextView timestampTextView;
//    }
//
//    public AbsBarPressureCustomAdapter(Context context, List<AbsBarPressure> objects) {
//        super(context, 0, objects);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        ViewHolder holder;
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
//
//            holder = new ViewHolder();
//            holder.valueTextView = convertView.findViewById(R.id.valueTextView);
//            holder.timestampTextView = convertView.findViewById(R.id.timestampTextView);
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        AbsBarPressure currentItem = getItem(position);
//
//        if (currentItem != null) {
//            int value = currentItem.getAbsBarPressureValue();
//            String timestamp = currentItem.getTimestamp();
//
//            holder.valueTextView.setText(String.valueOf(value));
//            holder.timestampTextView.setText(timestamp);
//            updateValuesPeriodically(holder, value, timestamp);
//        }
//
//        return convertView;
//    }
//
//    private void updateValuesPeriodically(final ViewHolder holder, final int value, final String timestamp) {
//        Runnable runnable = new Runnable() {
//            int currentValue = value;
//            String currentTimestamp = timestamp;
//
//            @Override
//            public void run() {
//                while (true) {
//                    currentValue = getNextValue(currentValue);
//                    currentTimestamp = getNextTimestamp(currentTimestamp);
//
//                    final int updatedValue = currentValue;
//                    final String updatedTimestamp = currentTimestamp;
//
//                    holder.valueTextView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            holder.valueTextView.setText(String.valueOf(updatedValue));
//                            holder.timestampTextView.setText(updatedTimestamp);
//                        }
//                    });
//
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        new Thread(runnable).start();
//    }
//
//    private int getNextValue(int currentValue) {
//        // Implement your logic for getting the next value
//        // This is just an example
//        return currentValue;
//    }
//
//    private String getNextTimestamp(String currentTimestamp) {
//        // Implement your logic for getting the next timestamp
//        // This is just an example
//        // You can convert the current timestamp to a long, increment it, and convert it back to a string
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date currentDate = dateFormat.parse(currentTimestamp);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(currentDate);
//            calendar.add(Calendar.SECOND, 2);
//            Date updatedDate = calendar.getTime();
//            return dateFormat.format(updatedDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return currentTimestamp;
//    }
//}