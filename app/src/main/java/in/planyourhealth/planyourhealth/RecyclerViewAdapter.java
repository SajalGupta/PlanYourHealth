package in.planyourhealth.planyourhealth;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Sajal on 12-05-2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflator;

    Context context;
    int j=0;
    List<RecyclerViewData> data = Collections.emptyList();
    public RecyclerViewAdapter(Context context,List<RecyclerViewData> data) {
        inflator=LayoutInflater.from(context);
        this.data=data;
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            View view = inflator.inflate(R.layout.custom_row,viewGroup,false);
            MyViewHolder holder = new MyViewHolder(view);
            Log.i("CreatingListandText","returning the normal crap "+j);
            j++;
            return holder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
            RecyclerViewData current = data.get(i);

            final MyViewHolder mVH = (MyViewHolder) viewHolder;
            mVH.title.setText(current.title);
            mVH.icon.setImageResource(current.iconID);
            if(i==0 || i==1) {
                mVH.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!Globals.mClicked[i]&&i==0) {
                            mVH.invisText.setText("My Reports");
                            mVH.invisText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(context,reportActivity.class);
                                    v.getContext().startActivity(i);
                                }

                            });
                            mVH.invistextOne.setText("My Transactions");
                            mVH.invisTextTwo.setText("Update Profile");
                            mVH.invisText.setVisibility(View.VISIBLE);
                            mVH.invistextOne.setVisibility(View.VISIBLE);
                            mVH.invisTextTwo.setVisibility(View.VISIBLE);
                            Globals.mClicked[i] = true;
                        }
                        else if(!Globals.mClicked[i]&&i==1){
                            mVH.invisText.setText("Call Us");
                            mVH.invistextOne.setText("Reach Us");
                            mVH.invisTextTwo.setText("Refund Policy");
                            mVH.invisText.setVisibility(View.VISIBLE);
                            mVH.invistextOne.setVisibility(View.VISIBLE);
                            mVH.invisTextTwo.setVisibility(View.VISIBLE);
                            Globals.mClicked[i] = true;
                        }
                        else {
                            mVH.invisText.setVisibility(View.GONE);
                            mVH.invistextOne.setVisibility(View.GONE);
                            mVH.invisTextTwo.setVisibility(View.GONE);
                            Globals.mClicked[i] = false;
                        }

                    }
                });
            }

    }




    @Override
    public int getItemCount() {
        return data.size();
    }






    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView icon;
        TextView invisText;
        TextView invistextOne;
        TextView invisTextTwo;
        TextView invisTextThree;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            invisText = (TextView) itemView.findViewById(R.id.invisText);
            invistextOne = (TextView) itemView.findViewById(R.id.invisTextOne);
            invisTextTwo = (TextView) itemView.findViewById(R.id.invisTextTwo);
            invisTextThree = (TextView) itemView.findViewById(R.id.invisTextThree);


        }



    }
}



