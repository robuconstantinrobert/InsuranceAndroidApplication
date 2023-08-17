//package com.example.incercarelicenta6.AbsBarPressure;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.incercarelicenta6.R;
//
//import java.util.List;
//
//public class AbsBarPressureAdapter extends BaseAdapter {
//    private Context context;
//    private List<AbsBarPressure> absBarPressureList;
//
//    public AbsBarPressureAdapter(Context context, List<AbsBarPressure> absBarPressureList) {
//        this.context = context;
//        this.absBarPressureList = absBarPressureList;
//    }
//
//    @Override
//    public int getCount() {
//        return absBarPressureList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return absBarPressureList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null)
//        {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.item_list, parent, false);
//        }
//        AbsBarPressure absBarPressure = absBarPressureList.get(position);
//
//        TextView valueTextView = convertView.findViewById(R.id.valueTextView);
//        TextView timestampTextView = convertView.findViewById(R.id.timestampTextView);
//
//        // Set the values in the UI components
//        valueTextView.setText(String.valueOf(absBarPressure.getAbsBarPressureValue()));
//        timestampTextView.setText(absBarPressure.getTimestamp());
//
//        return convertView;
//    }
//}
//
