package com.example.traveljournal2019.ui.holiday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal2019.HolidayListAdapter;
import com.example.traveljournal2019.R;
import com.example.traveljournal2019.model.Holiday;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HolidayFragment extends Fragment{

    private HolidayViewModel mHolidayViewModel;
    private NavController navController;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate (R.layout.fragment_holiday,container,false );
        TextView title = v.findViewById(R.id.text_holiday);
        title.setText("Holiday Fragment");

        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerview);
        final HolidayListAdapter adapter = new HolidayListAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        mHolidayViewModel = ViewModelProviders.of(this).get(HolidayViewModel.class);
        mHolidayViewModel.getAllHolidays().observe(getViewLifecycleOwner(), new Observer<List<Holiday>>() {
            @Override
            public void onChanged(@Nullable final List<Holiday> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setHolidays(words);
            }
        });


        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_menu_camera);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NavDirections action = HolidayFragmentDirections.actionHolidayListToHolidayInput();
                Navigation.findNavController(v).navigate(action);
            }
        });

        return v;
        }
    }