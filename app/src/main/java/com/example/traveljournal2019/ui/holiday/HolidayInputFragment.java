package com.example.traveljournal2019.ui.holiday;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.traveljournal2019.R;
import com.example.traveljournal2019.model.Holiday;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class HolidayInputFragment extends Fragment {
    private HolidayViewModel mHolidayViewModel ;
    private EditText mEditHolidayView ;

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
Bundle savedInstanceState ) {
// Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_holiday_input,container,false );
        mEditHolidayView = v.findViewById(R.id.holiday_name);
        mHolidayViewModel = ViewModelProviders.of( this ).get(HolidayViewModel.class);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource (R.drawable.ic_menu_camera);
        if(fab!=null) {
            fab.setOnClickListener(new View.OnClickListener() {
              @Override
             public void onClick (View view) {
                   if( TextUtils.isEmpty(mEditHolidayView.getText ())) {
                        Snackbar.make(view, " You ␣ need ␣to␣ enter ␣a␣ name ",Snackbar.LENGTH_LONG )
                                .setAction (" Action ", null ). show ();
                    } else {
                        Holiday h = new Holiday ( mEditHolidayView.getText().toString());
                        mHolidayViewModel.insert(h);
                       NavDirections action = HolidayInputFragmentDirections.actionHolidayInputToHolidayList();
                       Navigation.findNavController(v).navigate(action);
                    }
                }
            });
        }
        return v;
    }
}
