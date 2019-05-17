package com.example.cookingebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {


    private List<Recipe> recipes;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv = findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter(this);
    }

    private void initializeData() {
        recipes = new ArrayList<Recipe>();
        Recipe recipe = new Recipe(
                "Phở xào thịt bò thơm ngon ",
                "Cách làm phở xào thịt bò thơm ngon mà không hề khó",
                new String[]{"Thịt thăn bò tươi - 200gr", "Bánh phở - 200gr", "Hành tây - 1 củ", "Giá đỗ tươi - 1 lạng", "Tương nhạt - 1 muỗng canh", "Nước tương đen - 1 muỗng cà phê", "Dầu lạc - 1 muỗng canh", "Tương nhạt - 2 muỗng cà phê", "Rượu nấu - 2 muỗng cà phê", "Dầu mè - 2 muỗng cà phê"},
                new String[]{"Bánh phở mua về trần qua với nước nóng rồi để nguội, sau đó đánh cho tơi sợi bánh.\n" +
                        "\n" +
                        "Hành tây cắt lái múi cau mỏng.", "Ướp thịt bò: Thịt bò thái miếng mỏng rồi ướp cùng gia vị gồm: 2 muỗng cà phê tương nhạt; 2 muỗng cà phê rượu nấu; 1 muỗng cà phê đường; 2 muỗng cà phê dầu mè.\n" +
                        "\n" +
                        "Ướp trong vòng 15-20 phút để thịt ngấm đều gia vị.", "Phi thơm tỏi trong chảo dầu nóng rồi cho thịt bò vào xào trước.\n" +
                        "\n" +
                        "Khi thấy miếng thịt bò đã chín tương đối thì cho bánh phở vào xào cùng. Bánh phở chín khá nhanh, giảm lửa vừa và xào đều tay để để phở tơi, không bị cuộn vào với nhau.\n" +
                        "\n" +
                        "Có thể thêm chút nước nếu khô quá, nêm nếm lại gia vị cho vừa vặn vị bánh phở và thịt bò\n" +
                        "\n" +
                        "Sau đó, xào đến cạn nước thì rắc hành và múc ra đĩa."},
                2, 10, 0, 30, R.drawable.thumbnail1);
        recipes.add(recipe);
        recipe = new Recipe(
                "Gỏi Xoài Khô Bò Gia Vị",
                "Ngon Miễn Chê, Ăn Là Ghiền",
                new String[]{"Xoài xanh: 1 trái ", "Cà rốt: 1 củ ", "Khô bò: 100gr ", "Đường: 2 muỗng canh ", "Nước cốt chanh: 2 muỗng canh ", "Nước mắm: 2 muỗng canh ", "Tỏi băm: 1 muỗng canh ", "Ớt băm: 1 muỗng canh ", "Húng lủi (bạc hà): 10gr ", "Đậu phộng: 10gr"},
                new String[]{"Sơ chế nguyên liệu ", "Pha nước mắm trộn gỏi ", "Trộn gỏi xoài khô bò"},
                6, 20, 0, 30, R.drawable.thumbnail2);
        recipes.add(recipe);
        recipe = new Recipe(
                "Canh khổ qua nhồi thịt",
                "Ăn là ghiền",
                new String[]{"Khổ qua: 56 trái ", "200g thịt xay ", "23 miếng nấm mèo ", "Hành lá", " hành củ", " ngò rí ", "Tiêu", " muối", " bột nêm ", "Nước dùng hầm xương hoặc nước lã "},
                new String[]{"Nấm mèo ngâm nước cho nở mềm, rửa sạch, thái nhỏ. Hành lá cắt rễ, lá úa, giập, rửa sạch, phần đầu trắng đập giập, xắt nhỏ. Phần lá xanh để riêng. Hành củ bóc vỏ, rửa sạch, đập giập, bằm nhỏ. Rau ngò rí bỏ rễ, lá già úa, rửa sạch"
                        , "Thịt nạc vai rửa sạch, xay thật nhuyễn cho nhân không khô. Bạn cũng có thể thay bằng giò sống với tỷ lệ 2 phần thịt xay, 1 phần giò sống hoặc thay giò sống bằng cá thác lác. Cho thịt, nấm mèo, hành củ, đầu hành cùng chút bột nêm, tiêu trộn đều, ướp khoảng 15 phút."
                        , "Trong khi ướp thịt thì bạn chuẩn bị khổ qua. Khổ qua phải lựa trái mới hái, thuôn đều. Để dễ lấy ruột và món ăn được đẹp hơn thì đun một nồi nước. Nước sôi thêm 1 thìa muối và cho khổ qua vào chần khoảng 1 phút. Tắt bếp, cho khổ qua ngâm ngay vào nước lạnh."
                        , " Đun sôi nước dùng hầm từ xương. Nếu không có, bạn có thể dùng nước thường, nêm chút bột nêm. Khi nước sôi lần lượt thả khổ qua từng trái vào xoong, đun lửa lớn cho sôi bùng lên, hớt hết bọt cho nước dùng được trong, sau đó hầm đến khi chín mềm."},
                2, 10, 0, 30, R.drawable.thumbnail3);
        recipes.add(recipe);
        recipe = new Recipe(
                "Sụn heo rim cá mắm",
                "Cực đơn giản",
                new String[]{"500gr sườn non - ", "1 miếng cá mặn - ", "1 nhánh sả; 3 tép tỏi - ", "1 trái ớt sừng; 1 miếng gừng - ", "1/3 muỗng cà phê tiêu - ", "2 muỗng canh đường - ", "1 muỗng canh nước mắm - ", "3 phần trắng hành lá -"},
                new String[]{"Sả, hành, tiêu, gừng, tỏi cho vào cối giã nát."
                        , "Sụn chặt miếng nhỏ. Mắm cá băm nhỏ hay thái miếng nhỏ. Cho vào âu cùng 1/3 nguyên liệu giã nát cùng nước mắm và 1 muỗng đường."
                        , "Bắc chảo lên bếp, cho vào 1 muỗng canh dầu cùng đường còn lại. Khi nước đường có màu vàng nâu thì cho hết nguyên liệu giã nát còn lại vào xào thơm, sau đó cho sụn vào xào săn 4 phút.", "Cuối cùng cho 1 chén nước vào, đậy nắp rim lửa thấp cho đến khi sụn mềm, nước sánh. Nêm nếm lại cho vừa ăn là tắt bếp."},
                2, 10, 0, 30, R.drawable.thumbnail4);
        recipes.add(recipe);
        recipe = new Recipe(
                "Thịt kho hột vịt",
                "Cực dễ",
                new String[]{"Thịt heo: 500g - ", "Trứng vịt: 4-5 trái - ", "Nước dừa tươi: 400ml - ", "Hành tím", " nước màu dừa - ", "Muối", " đường", " nước mắm -"},
                new String[]{"Để làm món thịt kho này bạn có thể sử dụng thịt đùi hoặc thịt ba chỉ. Thịt thái miếng cỡ 4-5 cm. Đối với món thịt này bạn phải thái miếng lớn, to gấp 3-4 lần so với thịt kho bình thường. Sau khi thái bóp với 1 thìa muối hạt, sau đó rửa lại thật sạch."
                        , "Để món thịt kho ngon, thịt được ướp với 1/2 thìa đường rồi đem phơi nắng hoặc để nơi thoáng mát ít nhất 2-3h. Đây cũng là bước quan trọng để giúp món thịt kho của bạn ngon hơn."
                        , " Trứng vịt rửa sạch, luộc chín, bóc vỏ."
                        , "Cho nước dừa vào nồi, sau đó cho thịt vào kho. Nước sôi hớt hết bọt cho nước trong. Sau đó kho nhỏ cho đến khi thịt chín mềm. Khi thịt gần được cho trứng vào kho tiếp khoảng 15 phút, nêm 1 thìa nước mắm cho thơm là được."},
                2, 10, 0, 30, R.drawable.thumbnail5);
        recipes.add(recipe);

    }

    private void initializeAdapter(Context context) {
        RVAdapter adapter = new RVAdapter(recipes, context);
        rv.setAdapter(adapter);
    }
}
