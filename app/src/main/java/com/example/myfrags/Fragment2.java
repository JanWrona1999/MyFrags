package com.example.myfrags;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment2 extends Fragment {

    private FragsData fragsData;
    private Observer<Integer> numberObserver;

    private TextView text;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.slide_right));
        //setEnterTransition(inflater.inflateTransition(R.transition.fade));
        //setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_left));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        //1.
        text = (TextView) view.findViewById(R.id.current);
        button = (Button) view.findViewById(R.id.button_plus);

        //2.
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        //3.
        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer newInteger) {

                text.setText(newInteger.toString());
            }
        };

        //4.
        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        //5.
        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {


                Integer i = fragsData.counter.getValue();

                fragsData.counter.setValue(++i);

            }

        }
        );

        return view;
    }
}