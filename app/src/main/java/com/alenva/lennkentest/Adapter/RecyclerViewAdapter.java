package com.alenva.lennkentest.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alenva.lennkentest.Class.Info_Gas;
import com.alenva.lennkentest.Model.Gas;
import com.alenva.lennkentest.R;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Gas> gasList;
    public RecyclerViewAdapter(Context context, List<Gas> GasList){
        this.context = context;
        this.gasList=  GasList;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_recyclerview, viewGroup ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Gas gas = gasList.get(position);
        viewHolder.title.setText(gas.getName());



    }

    @Override
    public int getItemCount() {

        return gasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView gas_name;
        public ImageView icon_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.name_text_row);
            gas_name = itemView.findViewById(R.id.title_row);
            icon_image= itemView.findViewById(R.id.image_row);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Gas gas = gasList.get(position);
                    Intent intent = new Intent(context, Info_Gas.class);

                    intent.putExtra("name", gas.getName());
                    intent.putExtra("street", gas.getStreet());
                    intent.putExtra("postalcode", gas.getPostalcode());
                    intent.putExtra("regular", gas.getRegular());
                    intent.putExtra("premium", gas.getPremium());
                    intent.putExtra("rfc", gas.getRfc());
                    intent.putExtra("lat",gas.getLatitude());
                    intent.putExtra("lon", gas.getLongitude());

                    context.startActivity(intent);





                }


            });

        }
    }
}
