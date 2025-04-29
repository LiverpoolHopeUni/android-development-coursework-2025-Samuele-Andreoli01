package uk.ac.hope.mcse.android.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import uk.ac.hope.mcse.android.coursework.databinding.FragmentSecondBinding;
import uk.ac.hope.mcse.android.coursework.model.Note;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private int notePosition = -1;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTextTitle = binding.editTextTitle;
        EditText editTextDescription = binding.editTextDescription;

        // Get the note details from the arguments
        Bundle args = getArguments();
        if (args != null) {
            notePosition = args.getInt("position", -1);
            String title = args.getString("title", "No Title");
            String description = args.getString("description", "No Description");
            editTextTitle.setText(title);
            editTextDescription.setText(description);
        }

        binding.buttonSecond.setOnClickListener(v -> {
            if (notePosition != -1) {
                String newTitle = editTextTitle.getText().toString();
                String newDescription = editTextDescription.getText().toString();

                if (!newTitle.isEmpty() && !newDescription.isEmpty()) {
                    FirstFragment.updateNote(notePosition, new Note(newTitle, newDescription));
                }
            }
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}