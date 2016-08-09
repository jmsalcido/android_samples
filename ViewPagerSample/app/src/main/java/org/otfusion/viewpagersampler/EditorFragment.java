package org.otfusion.viewpagersampler;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditorFragment extends Fragment {

    public static final String KEY = "KEY";

    private EditText mEditText;

    public static Fragment newInstance(int position) {
        EditorFragment editorFragment = new EditorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        editorFragment.setArguments(bundle);
        return editorFragment;
    }

    public EditorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.editor_fragment, container, false);
        mEditText = (EditText) view.findViewById(R.id.editor);
        mEditText.setHint("Pager: #" + (getArguments().getInt(KEY) + 1));
        return view;
    }
}
