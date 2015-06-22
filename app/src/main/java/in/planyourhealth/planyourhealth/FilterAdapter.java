package in.planyourhealth.planyourhealth;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static android.view.View.GONE;

/**
 * Created by Sajal on 20-06-2015.
 */
public class FilterAdapter extends BaseAdapter{
    Context mContext;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> mProductsList=null;
    ArrayList<HashMap<String, String>> randomFreeList;
    public static int cartArrayIndex=Globals.cartFilled;
    public static ProductCart[] mCart = Globals.getCart();
    private static final String TAG_TYPE = "product_type";
    private static final String TAG_NAME = "product_name";
    public static final String BROADCAST_ACTION = "Hello";
    public static float totalPrice;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        final View finalConvertView = convertView;
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Adding "+holder.productType.getText(),Toast.LENGTH_SHORT).show();

                mCart[Globals.cartFilled] = new ProductCart();
                mCart[Globals.cartFilled].setProductID(position);
                mCart[Globals.cartFilled].setProductPrice((float) 50.00);
                mCart[Globals.cartFilled].setProductName(holder.productName.getText().toString());
                mCart[Globals.cartFilled].setProductType(holder.productType.getText().toString());
                Log.i("CartCrap", mCart[Globals.cartFilled].getProductID() + " " + mCart[Globals.cartFilled].getProductType());
                Globals.setCart(mCart);
                Intent i = new Intent(BROADCAST_ACTION);
                //i.putExtra("price",mCart[Globals.cartFilled].getProductPrice());
                //i.putExtra("totalItems",Globals.cartFilled);
                mContext.sendBroadcast(i);

                Globals.cartFilled++;
                final Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
                finalConvertView.startAnimation(animation);
                mProductsList.remove(position);
                notifyDataSetChanged();


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
