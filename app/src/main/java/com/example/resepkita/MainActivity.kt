package com.example.resepkita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.PaddingValues




import com.example.resepkita.ui.theme.ResepkitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResepkitaTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

data class Recipeku(
    val name: String,
    val desc: String,
    val bahan: List<String>,
    val recipeSteps : List<String>,
    val pictureId: Int? = null
)

val recipeMenu = listOf(
    Recipeku(
        name = "Spaghetti Carbonara",
        desc = "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.",
        bahan = listOf(
            "200g spaghetti",
            "100g pancetta",
            "2 eggs",
            "50g grated Parmesan cheese",
            "50g grated Pecorino cheese",
            "Salt and pepper",
            "Olive oil"
        ),
        recipeSteps = listOf(
            "Cook spaghetti according to package instructions.",
            "Fry pancetta in olive oil until crispy.",
            "Beat eggs and mix with Parmesan and Pecorino cheeses.",
            "Combine spaghetti with pancetta and egg mixture.",
            "Season with salt and pepper, then serve."
        ),
        pictureId = R.drawable.carbonara
    ),
    Recipeku(
        name = "Beef Wellington",
        desc = "A luxurious dish consisting of beef tenderloin coated with mushroom duxelles and wrapped in puff pastry.",
        bahan = listOf(
            "1 kg beef tenderloin",
            "200g mushrooms",
            "2 tbsp Dijon mustard",
            "400g puff pastry",
            "1 egg",
            "Salt and pepper",
            "Olive oil"
        ),
        recipeSteps = listOf(
            "Sear beef in olive oil and brush with Dijon mustard.",
            "Finely chop mushrooms and cook to make a duxelles.",
            "Wrap beef in mushroom duxelles, then in puff pastry.",
            "Brush with beaten egg and bake at 200°C for 40 minutes."
        ),
        pictureId = R.drawable.beefwellington
    ),
    Recipeku(
        name = "Chicken Alfredo",
        desc = "A creamy pasta dish with grilled chicken, garlic, butter, and Parmesan cheese.",
        bahan = listOf(
            "200g fettuccine",
            "2 chicken breasts",
            "2 cloves garlic, minced",
            "100g butter",
            "200ml heavy cream",
            "50g Parmesan cheese",
            "Salt and pepper"
        ),
        recipeSteps = listOf(
            "Cook fettuccine according to package instructions.",
            "Grill chicken and slice into strips.",
            "Melt butter in a pan, add garlic and cook until fragrant.",
            "Add cream and Parmesan, simmer until thickened.",
            "Combine with pasta and grilled chicken, season with salt and pepper."
        ),
        pictureId = R.drawable.chickenalfredo
    ),
    Recipeku(
        name = "Caesar Salad",
        desc = "A salad made with romaine lettuce, croutons, Parmesan cheese, and Caesar dressing.",
        bahan = listOf(
            "1 head romaine lettuce",
            "100g croutons",
            "50g Parmesan cheese",
            "Caesar dressing",
            "Salt and pepper"
        ),
        recipeSteps = listOf(
            "Tear lettuce into bite-sized pieces.",
            "Toss lettuce with Caesar dressing.",
            "Add croutons and Parmesan cheese on top.",
            "Season with salt and pepper, then serve."
        ),
        pictureId = R.drawable.caesarsalad
    ),
    Recipeku(
        name = "Fish and Chips",
        desc = "Crispy battered fish served with thick-cut fries and tartar sauce.",
        bahan = listOf(
            "2 white fish fillets",
            "1 cup flour",
            "1 egg",
            "1 cup sparkling water",
            "Potatoes for chips",
            "Salt and pepper",
            "Oil for frying"
        ),
        recipeSteps = listOf(
            "Make batter by mixing flour, egg, and sparkling water.",
            "Dip fish fillets in batter and fry in hot oil until crispy.",
            "Cut potatoes into fries and fry until golden.",
            "Serve fish with chips and tartar sauce."
        ),
        pictureId = R.drawable.fishandchips
    )
)


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = modifier,
        color = Color(0xFFF5F5DC)
    ) {
        if (shouldShowOnboarding) {
            WelcomeScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            RecipeApp()
        }
    }
}

@Composable
fun WelcomeScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color(0xFFFFF9C4)) // Light yellow background color
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gradient color for title text
            Text(
                text = "Resep Kita",
                color = Color(0xFFFF5722),
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,

            )
            Spacer(modifier = Modifier.height(72.dp)) // Increased space for better layout
            Image(
                painter = painterResource(id = R.drawable.koki),
                contentDescription = "Chef",
                modifier = Modifier
                    .size(220.dp), // Slightly larger logo for more impact
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp)) // Adjusted space for better alignment
            Text(
                text = "The Ultimate Collection of Delicious Recipes", // Refined text
                color = Color(0xFFFFEB3B),
                fontSize = 18.sp, // Slightly larger for better readability
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 172.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onContinueClicked,
                modifier = Modifier
                    .width(340.dp) // Slightly wider button for a balanced look
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFEB3B),
                    contentColor = Color(0xFFFF5722)
                ),
                shape = RoundedCornerShape(13.dp) // Rounded corners for a modern look
            ) {
                Text(
                    text = "Explore Recipe",
                    fontSize = 30.sp
                )
            }
        }
    }
}


@Composable
fun RecipeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu_list") {
        composable("menu_list") {
            ListRecipeScreen(
                menuItems = recipeMenu,
                onItemClick = { recipeku ->
                    navController.navigate("recipe_detail/${recipeku.name}")
                }
            )
        }
        composable("recipe_detail/{foodName}") { backStackEntry ->
            val foodName = backStackEntry.arguments?.getString("foodName")
            val foodItem = recipeMenu.find { it.name == foodName }
            foodItem?.let {
                DetailRecipeScreen(recipeItem = it, onBackClick = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListRecipeScreen(
    menuItems: List<Recipeku>,
    onItemClick: (Recipeku) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val filteredItems = menuItems.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Resep Kita",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50), // Changed to a fresh green
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Search Bar with Cooking Theme
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(
                        color = Color(0xFFF4F1EE), // Light cream color for a warm cooking feel
                        shape = MaterialTheme.shapes.medium
                    ),
                placeholder = {
                    Text(
                        text = "Discover Recipes",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFB6A68D)) // Pakai bodyMedium
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear search",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { /* Handle search action if needed */ }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFFB6A68D),
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium
            )

// Text below the search bar
            Text(
                text = "Here are some available recipes...",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF8C6B3F),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )


            // Menu Items
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (filteredItems.isEmpty()) {
                    item {
                        Text(
                            text = "Try searching again, no recipes found.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(filteredItems) { foodItem ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                        ) {
                            ItemRecipe(foodItem = foodItem, onClick = { onItemClick(foodItem) })
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ItemRecipe(foodItem: Recipeku, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = Color(0xFFFFF9C4), // Ganti ke kuning muda
        shape = RoundedCornerShape(12.dp), // Sedikit lebih membulat biar modern
        shadowElevation = 4.dp, // Tambah shadow biar kesan "floating"
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp) // Padding lebih proporsional
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Image Thumbnail
                if (foodItem.pictureId != null) {
                    Image(
                        painter = painterResource(id = foodItem.pictureId),
                        contentDescription = "Image of ${foodItem.name}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                color = Color(0xFFFFF59D),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = foodItem.name.first().toString(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF2E7D32) // Hijau tua
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFF2E7D32) // Hijau tua
                )
            }
            ElevatedButton(
                onClick = onClick,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color(0xFFFF5722), // Oranye
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Detail Recipe")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRecipeScreen(recipeItem: Recipeku, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        recipeItem.name,
                        style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF5722) // Oranye
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC8E6C9)), // Background hijau
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    shadowElevation = 6.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (recipeItem.pictureId != null) {
                        Image(
                            painter = painterResource(id = recipeItem.pictureId),
                            contentDescription = "Image of ${recipeItem.name}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .background(
                                    color = Color(0xFFC8E6C9),
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No Image Available",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = Color(0xFF2E7D32)
                                )
                            )
                        }
                    }
                }
            }

            item { SectionCard(title = "✨ About this Dish", text = recipeItem.desc) }
            item { SectionCard(title = "\uD83D\uDED2 What You Need ", textList = recipeItem.bahan) }
            item { SectionCard(title = "\t\uD83C\uDF73 How to Cook", textList = recipeItem.recipeSteps) }
        }
    }
}



@Composable
fun SectionCard(title: String, text: String? = null, textList: List<String>? = null) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Teks biasa
            text?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF616161)
                    )
                )
            }

            // List teks (Ingredients atau Cooking Steps)
            textList?.forEachIndexed { index, item ->
                Text(
                    text = "${index + 1}. $item",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF616161)
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ResepkitaTheme {
        RecipeApp()
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeAppPreview() {
    ResepkitaTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    ResepkitaTheme {
        WelcomeScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DetailRecipePreview() {
    ResepkitaTheme {
        DetailRecipeScreen(
            recipeItem = recipeMenu[0],
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListRecipePreview() {
    ResepkitaTheme {
        ListRecipeScreen(
            menuItems = recipeMenu,
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemRecipePreview() {
    ResepkitaTheme {
        ItemRecipe(
            foodItem = recipeMenu[0],
            onClick = {}
        )
    }
}