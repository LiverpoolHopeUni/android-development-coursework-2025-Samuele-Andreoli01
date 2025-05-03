package uk.ac.hope.mcse.android.coursework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import java.util.ArrayList;
import java.util.List;
import uk.ac.hope.mcse.android.coursework.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private OnNoteClickListener listener;
    private OnDeleteClickListener deleteListener;

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public NoteAdapter(List<Note> noteList, OnNoteClickListener listener, OnDeleteClickListener deleteListener) {
        this.noteList = new ArrayList<>(noteList);
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.textView.setText(note.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onNoteClick(note));
        holder.itemView.setOnLongClickListener(v -> {
            holder.deleteIcon.setVisibility(View.VISIBLE);
            return true;
        });
        holder.deleteIcon.setOnClickListener(v -> {
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Confirm Delete")
                    .setMessage("Are you sure to delete?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (deleteListener != null) {
                            deleteListener.onDeleteClick(position);
                        }
                        holder.deleteIcon.setVisibility(View.GONE);
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        holder.deleteIcon.setVisibility(View.GONE);
                    })
                    .setOnCancelListener(dialog -> holder.deleteIcon.setVisibility(View.GONE))
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void updateNotes(List<Note> newNotes) {
        this.noteList.clear();
        this.noteList.addAll(newNotes);
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView deleteIcon;

        NoteViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.noteTitle);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
        }
    }
}