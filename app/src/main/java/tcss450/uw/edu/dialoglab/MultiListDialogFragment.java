package tcss450.uw.edu.dialoglab;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MultiListDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultiListDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MultiListDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MultiListDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MultiListDialogFragment newInstance(String param1, String param2) {
        MultiListDialogFragment fragment = new MultiListDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final List mSelectItems = new ArrayList(); //tracks selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //set dialog title
        builder.setTitle(R.string.pick_toppings)
        //Specify the list array. The items to be select by default (null for none).
        //The listener receives callbacks when item(s) are selected
        .setMultiChoiceItems(R.array.toppings_array, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    //if user checked item, add it to the selected items
                    mSelectItems.add(which);
                } else if (mSelectItems.contains(which)) {
                    //if item is already in array remove it
                    mSelectItems.remove(Integer.valueOf(which));
                }
            }
        })
        //setup buttons
        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //User clicked Okay, save mSelectedItems results somewhere
                //or return them to the component that opened the dialog
                Resources res = getActivity().getResources();
                String[] toppings = res.getStringArray(R.array.toppings_array);
                StringBuilder builder = new StringBuilder();
                for(int i = 0; i< mSelectItems.size(); i++){
                    builder.append(toppings[(int) mSelectItems.get(i)]);
                    builder.append(" ");
                }
                Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        })
       .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });
    return builder.create();
    }

}
