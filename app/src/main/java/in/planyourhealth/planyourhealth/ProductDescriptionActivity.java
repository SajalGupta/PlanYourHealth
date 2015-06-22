package in.planyourhealth.planyourhealth;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ProductDescriptionActivity extends ActionBarActivity {
    private Toolbar toolbar;
    public static final String BROADCAST_ACTION = "Hello";
    public static ProductCart[] mCart = Globals.getCart();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        toolbar = (Toolbar)(findViewById(R.id.app_bar));
        setSupportActionBar(toolbar);


        Bundle productData = getIntent().getExtras();
        final TextView productName = (TextView) findViewById(R.id.prodNameAct);
        final TextView productType = (TextView) findViewById(R.id.prodTypeAct);
        productName.setText(productData.getString("productName"));
        productType.setText(productData.getString("productType"));
        Button addToCart = (Button) findViewById(R.id.addToCartAct);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CartFilledValue",Globals.cartFilled+"");
                mCart[Globals.cartFilled] = new ProductCart();
                mCart[Globals.cartFilled].setProductName(productName.getText().toString());
                mCart[Globals.cartFilled].setProductType(productType.getText().toString());
                mCart[Globals.cartFilled].setProductPrice((float) 50.0);
                Intent b = new Intent(BROADCAST_ACTION);
                sendBroadcast(b);
                Toast.makeText(ProductDescriptionActivity.this,"Added",Toast.LENGTH_SHORT);
                for(int i =0;i<=Globals.cartFilled;i++){
                    Log.i("CartData",mCart[i].getProductName().toString()+"  "+mCart[i].getProductType().toString());
                }
                Globals.cartFilled = Globals.cartFilled + 1;
                Log.i("CartFilledValueAfter",Globals.cartFilled+"");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
