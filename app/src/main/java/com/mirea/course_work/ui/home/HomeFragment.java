package com.mirea.course_work.ui.home;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mirea.course_work.R;
import com.mirea.course_work.University;
import com.mirea.course_work.UniversityDao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HomeFragment extends Fragment {

    public void newThread(Runnable runnable){
        Thread t = new Thread(runnable);
        t.start();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout mainScroll = layout.findViewById(R.id.main_scroll);
        UniversityDao dao = App.getInstance().getDatabase().universityDao();

        List<University> all = dao.getAll();

        for (University university : all) {
            CardView universityCard = createUniversityCard(university);
            mainScroll.addView(universityCard);
        }
        //TODO добавить отступы
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public CardView createUniversityCard(University university) {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        CardView cardView = (CardView) inflater.inflate(R.layout.card, null, false);
        //ViewGroup.
        ImageView image;
        TextView name;
        TextView isGov;
        TextView haveDorm;
        TextView city;
        image = cardView.findViewById(R.id.image);
        name = cardView.findViewById(R.id.name);
        isGov = cardView.findViewById(R.id.isGov);
        haveDorm = cardView.findViewById(R.id.haveDorm);
        city = cardView.findViewById(R.id.city);

        //image.getDrawable(university.image);
        name.setText(university.name);
        isGov.setText(university.isGov ? "не государственный" : "государственный");
        if (university.haveDorm) {
            haveDorm.setText("с общежитием");
        } else {
            haveDorm.setText("без общежития");
        }
        city.setText(university.city);

        cardView.setOnClickListener(v -> {
            MireaFragment mireaFragment = new MireaFragment();

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mireaFragment).commit();

            //TODO проверить
        });
        return cardView;
    }
}