package com.example.parkeerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkeerapp.utils.UserSessionManager;

public class WalletFragment extends Fragment {

    private SharedPreferences prefs;
    private TextView txvSaldo, txvPhone;
    private ImageView imvTopup;

    public WalletFragment() {
        // Required empty public constructor
    }

    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Kosong karena kita tidak pakai parameter ARG
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().getWindow().setStatusBarColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
            );
        }

        txvSaldo = view.findViewById(R.id.txvSaldo);
        txvPhone = view.findViewById(R.id.txvPhone);
        imvTopup = view.findViewById(R.id.imvTopup);

        // Get current user's email
        UserSessionManager session = new UserSessionManager(requireContext());
        String email = session.getEmail();
        String phone = session.getPhone();

        if (phone.length() >= 10) {
            String masked = phone.substring(0, 2) + "******" + phone.substring(phone.length() - 2);
            txvPhone.setText(masked);
        } else {
            txvPhone.setText(phone);
        }

        // Use SharedPreferences with unique key per user
        prefs = requireContext().getSharedPreferences("wallet_" + email, Context.MODE_PRIVATE);

        // Tampilkan saldo saat ini
        updateBalance();

        // Logic top-up saldo Rp10.000
        imvTopup.setOnClickListener(v -> {
            int currentBalance = prefs.getInt("balance", 0);
            int newBalance = currentBalance + 10000;

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("balance", newBalance);
            editor.apply();

            updateBalance();
            Toast.makeText(requireContext(), "Top-up Rp10.000 berhasil", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateBalance() {
        int balance = prefs.getInt("balance", 0);
        txvSaldo.setText("IDR " + String.format("%,d", balance).replace(',', '.'));
    }
}
