package com.example.rainstick.ui.settings;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rainstick.MainActivity;
import com.example.rainstick.databinding.FragmentSettingsBinding;

public class RainStickSettings extends Fragment {

    private FragmentSettingsBinding binding;
    private String userLocationString = null;
    private String defaultLocationString = "Seattle, WA, USA";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button locationButton = binding.locationButton;
        EditText userLocationSet = binding.userLocationSet;
        TextView userLocationGet = binding.userLocationGet;

        loadUserLocation();

        if (userLocationString != null) {
            userLocationGet.setText(userLocationString);
        } else {
            userLocationGet.setText(defaultLocationString);
        }

        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                userLocationString = userLocationSet.getText().toString();
                boolean successfulSave = ((MainActivity)getActivity()).putStringValue("userLocation", userLocationString);
                System.out.println("User location save status: " + successfulSave + "\n");
                loadUserLocation();
                userLocationGet.setText(userLocationString);
            }
        });

        settingsViewModel.getText().observe(getViewLifecycleOwner(), userLocationGet::setText);
        return root;
    }

    public void loadUserLocation() {
        userLocationString = ((MainActivity)getActivity()).getStringValue("userLocation");
        System.out.println("userLocationString: " + userLocationString + "\n");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}