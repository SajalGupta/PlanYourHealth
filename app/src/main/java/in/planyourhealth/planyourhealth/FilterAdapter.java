package in.planyourhealth.planyourhealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Sajal on 20-06-2015.
 */
public class FilterAdapter extends BaseAdapter{
    Context mContext;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> mProductsList=null;
    ArrayList<HashMap<String, String>> randomFreeList;
    private static final String TAG_TYPE = "product_type";
    private static final String TAG_NAME = "product_name";
    public FilterAdapter(Context context,ArrayList<HashMap<String, String>> productsList) {
        mContext = context;
        mProductsList = productsList;
        inflater = LayoutInflater.from(mContext);
        this.randomFreeList = new ArrayList<HashMap<String, String>>();
        this.randomFreeList.addAll(mProductsList);

    }
    public class ViewHolder {
        TextView productType;
        TextView productName;
        ImageView addButton;

    }

    @Override
    public int getCount() {
        return mProductsList.size();
    }

    @Override
    public HashMap getItem(int position) {
        return mProductsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item,null);
            holder.productName = (TextView)convertView.findViewById(R.id.prod_name);
            holder.productType = (TextView)convertView.findViewById(R.id.prod_type);
            holder.addButton = (ImageView) convertView.findViewById(R.id.addButton);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.productType.setText(mProductsList.get(position).get(TAG_TYPE));
        holder.productName.setText(mProductsList.get(position).get(TAG_NAME));
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Adding "+holder.productType.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        mProductsList.clear();
        if(charText.length()==0){
            mProductsList.addAll(randomFreeList);
        }
        else{
            for(HashMap<String, String> mpl : randomFreeList){
                if(mpl.get(TAG_TYPE).toLowerCase(Locale.getDefault()).contains(charText)){
                    mProductsList.add(mpl);
                }
            }
        }
        notifyDataSetChanged();
    }
}
