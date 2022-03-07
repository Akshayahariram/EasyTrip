package com.hp.easytrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hp.easytrip.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.policeNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),police_nearby.class));
            }
        }); binding.policeBulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),police_nearby.class));
            }
        }); binding.nearbyHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),police_nearby.class));
            }
        });binding.nearbyLodge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),police_nearby.class));
            }
        });
        binding.Kedarnath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Details.class)
                .putExtra("NAME","Kedarnath")
                .putExtra("CONTENT","One of the most revered temple destinations of India, Kedarnath town is nestled in the mighty Garhwal Himalayas. The town, built around the revered Kedarnath temple, is located at an altitude of 3,580 m, near Chorabari glacier, which is the source of the Mandakini river. Dedicated to Lord Shiva, the ancient temple has exquisite architecture and is built of extremely large but evenly shaped grey stone slabs. A conical rock formation inside the temple is worshipped as Lord Shiva in his “Sadashiva” form.  The Kedarnath temple, dedicated to Lord Shiva, is a part of Char Dham pilgrimage circuit, and is one of the 12 Jyotirlingas of Lord Shiva in India. Behind the Kedarnath temple, stand the Kedarnath peak, Kedar Dome and other Himalayan peaks.\n" +
                        "\n" +
                        "The historical name of this region is \"Kedar Khand\" and legend says, the Pandavas from the epic Mahabharata, after having defeated the Kauravas, felt guilty of having killed so many people and sought the blessings of Lord Shiva for redemption. The Lord eluded them repeatedly and took refuge at Kedarnath in the form of a bull. The Lord dived into the ground, leaving his hump on the surface at Kedarnath. The remaining portions of Lord Shiva appeared at four other places and are worshipped there as his manifestations. The arms of the Lord appeared at Tungnath, the face at Rudranath, the belly at Madmaheshwar and his locks (hair) at Kalpeshwar. The Kedarnath and four above mentioned shrines make the revered Panch Kedar pilgrimage circuit. For booking a pilgrimage, visit https://badrinath-kedarnath.gov.in/common/kedarnath.\n" +
                        "\n" +
                        "BEST TIME TO VISIT\n" +
                        "\n" +
                        "From May to October is the best time to visit Kedarnath. During winter months, the town remains closed due to heavy snowfall. The temple has been shut for the winter of 2021-22.\n" +
                        "\n" +
                        "Where to stay\n" +
                        "\n" +
                        "KEDARNATH (Rudra Meditation Cave) Kedarnath (Swargarohini Complex)\n" +
                        "\n" +
                        "Itineraries"));
            }
        }); binding.Yamunotri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Details.class)
                        .putExtra("NAME","Yamunotri")
                .putExtra("CONTENT","Yamunotri is a part of Char Dham (along with Gangotri, Kedarnath and Badrinath), the four most revered Hindu pilgrimages in the Himalayas. The small mountain hamlet, with the Yamunotri Temple at its centre, attracts thousands of devotees every year and is the commencing point of the Char Dham Yatra pilgrimage (May to October), which proceeds from Yamunotri to Gangotri and finally to Kedarnath and Badrinath. Lodged in a narrow gorge, close to the source of the Yamuna, the Yamunotri Temple is dedicated to Yamuna, the second-most sacred river after the Ganges. A dip in River Yamuna is said to protect one from untimely death.  Devotees either walk or ride a palanquin or a pony to reach the temple (around 3,233 m above sea level) from Janki Chatti, a steep trek of about 3 km that takes about 3 hours. \n" +
                        "\n" +
                        "Best Time To Visit: April to June and September to November"));
            }
        }); binding.Gangotri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Details.class)
                        .putExtra("NAME","Gangotri")
                .putExtra("CONTENT","One of the char dhams (the most sacred pilgrimage circuits in northern India with four holy destinations), Gangotri, in Uttarkashi, is a small town with the temple of Goddess Ganga at its heart. A 12-hour drive from Rishikesh, Gangotri is nestled among lofty Garhwal Himalayan peaks, glaciers and dense forests, and is one of highest pilgrimages in India (approx 3,415 m). Other than its divine atmosphere, Gangotri offers stunning vistas all around. According to Hindu legends, the most sacred of all rivers, Ganges (or Ganga), descended from heaven to earth at Gangotri, when Lord Shiva released the mighty river from his locks. The actual origin of the river is at Gaumukh in the Gangotri glacier, 19 km away from Gangotri and is accessible by trekking. After it originates from Gaumukh, the river is known as Bhagirathi and it acquires the name 'Ganga' after the river Alaknanda merges into it near the town of Devaprayag.\n" +
                        "\n" +
                        "When to visit\n" +
                        "Gangotri becomes a bustling hub of devotees during the Char Dham Yatra season (May to October).\n" +
                        "\n" +
                        "Where to stay\n" +
                        "Gangotri (Yatri Niwas)"));
            }
        }); binding.Badrinath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Details.class)
                        .putExtra("NAME","Badrinath")
                .putExtra("CONTENT","The Badrinath Temple also known as the Badrinarayan Temple, located in Uttarakhand's Badrinath town, is one of the four Char Dhams (four important pilgrimages) in the state. There are four pilgrim-destinations namely Yamunotri, Gangotri, Kedarnath, and Badrinath, collectively known as Char Dham. These pilgrimage centres draw large number of pilgrims each year, thus becoming the most important hubs of religious travel in the whole of Northern India. \n" +
                        "\n" +
                        "Badrinath is located at an elevation of around 3,100 m. Located in the Garhwal Himalayas, on the banks of the Alaknanda river, this sacred town lies between Nar and Narayana mountain ranges. The temple is believed to have been established by sage Adi Shankaracharya in the 8th century. With Lord Vishnu as its presiding deity, the temple remains open for six months in a year. In winter it becomes inaccessible due to heavy snowfall.\n" +
                        "\n" +
                        "BEST TIME TO VISIT\n" +
                        "\n" +
                        "The ideal time to visit Badrinath Temple is from May to October. The temple is closed from October/ November, and is reopened around April.\n" +
                        "\n" +
                        "Where To Stay\n" +
                        "\n" +
                        "Badrinath (Yatri Niwas)"));
            }
        });

    }
}