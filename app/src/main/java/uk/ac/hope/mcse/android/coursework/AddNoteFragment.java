package uk.ac.hope.mcse.android.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import uk.ac.hope.mcse.android.coursework.model.Note;

// Fragment for adding a new note with title and description
public class AddNoteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI components
        EditText editTextTitle = view.findViewById(R.id.editTextTitle);
        EditText editTextDescription = view.findViewById(R.id.editTextDescription);
        Button buttonSave = view.findViewById(R.id.buttonSave);

        // Set up save button click listener to add a new note
        buttonSave.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            // Validate input and save note if fields are non-empty
            if (!title.isEmpty() && !description.isEmpty()) {

                FirstFragment.addNote(new Note(title, description));
                // Navigate back to FirstFragment
                NavHostFragment.findNavController(AddNoteFragment.this)
                        .navigate(R.id.action_addNoteFragment_to_FirstFragment);
            }
        });
    }
}