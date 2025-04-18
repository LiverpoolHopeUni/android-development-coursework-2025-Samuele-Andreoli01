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

/**
 *
 */
public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private static List<Note> noteList = new ArrayList<>();
    private NoteAdapter noteAdapter;

    public static void addNote(Note note) {
        noteList.add(note);
    }

//    @Override
//    public View onCreateView(
//            @NonNull LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState
//    ) {
//
//        binding = FragmentFirstBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//
//    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        binding.buttonFirst.setOnClickListener(v ->
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
//        );
//    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up RecyclerView
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recyclerView);
//        if (recyclerView == null) {
//        }

        binding.buttonFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}