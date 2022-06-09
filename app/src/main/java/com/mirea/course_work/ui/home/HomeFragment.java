package com.mirea.course_work.ui.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.mirea.course_work.MainActivity;
import com.mirea.course_work.R;
import com.mirea.course_work.University;
import com.mirea.course_work.UniversityDao;
import com.mirea.course_work.ui.profile.ProfileFragment;

import java.util.List;

public class HomeFragment extends Fragment {

    private static HomeFragment instance;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
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

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        cardView.setLayoutParams(lp);

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

        //int imageResource = getResources().getIdentifier(university.image, null,);
        Drawable res = getResources().getDrawable(university.image);
        image.setImageDrawable(res);

        name.setText(university.name);
        if (university.isGov) {
            isGov.setText("не государственный");
        } else {
            isGov.setText("государственный");
        }
        if (university.haveDorm) {
            haveDorm.setText("с общежитием");
        } else {
            haveDorm.setText("без общежития");
        }
        city.setText(university.city);

        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), MireaActivity.class);
            startActivity(intent);
            //TODO проверить
        });

        return cardView;
    }
}