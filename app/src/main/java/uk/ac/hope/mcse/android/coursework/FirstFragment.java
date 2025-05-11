package uk.ac.hope.mcse.android.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import uk.ac.hope.mcse.android.coursework.databinding.FragmentFirstBinding;
import uk.ac.hope.mcse.android.coursework.model.Note;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private static List<Note> noteList = new ArrayList<>(); // Shared list to store all notes
    private NoteAdapter noteAdapter;

    // Add a new note to the list
    public static void addNote(Note note) {
        noteList.add(note);
    }

    // Update an existing note at the specified position
    public static void updateNote(int position, Note updatedNote) {
        if (position >= 0 && position < noteList.size()) {
            noteList.set(position, updatedNote);
        }
    }

    // Delete a note at the specified position
    public static void deleteNote(int position) {
        if (position >= 0 && position < noteList.size()) {
            noteList.remove(position);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout and bind views using ViewBinding
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noteAdapter = new NoteAdapter(noteList, note -> {
            // Handle note click to navigate to details
            int position = noteList.indexOf(note);
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putString("title", note.getTitle());
            bundle.putString("description", note.getDescription());
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
        },
    position -> {
            // Handle note deletion and update the adapter
            deleteNote(position);
            noteAdapter.updateNotes(noteList);
        });
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the list when returning to this fragment
        noteAdapter.updateNotes(noteList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}