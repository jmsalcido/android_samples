package org.otfusion.stupidrx.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.otfusion.stupidrx.R;

import java.util.ArrayList;
import java.util.List;

class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {

    private List<String> holidays = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;

    public HolidayAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.holiday_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(holidays.get(position));
    }

    public List<String> getData() {
        return holidays;
    }

    @Override
    public int getItemCount() {
        return holidays.size();
    }

    public void setHolidays(List<String> holidays) {
        this.holidays.clear();
        this.holidays.addAll(holidays);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mHolidayNameTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mHolidayNameTextView = (TextView) itemView.findViewById(R.id.holiday_name);
        }

        void bind(String name) {
            mHolidayNameTextView.setText(name);
        }
    }
}
