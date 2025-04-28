package com.example.resepkita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resepkita.ui.theme.ResepkitaTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.text.font.FontStyle

// Modern App Color Scheme
object ModernAppColors {
    val Primary = Color(0xFFF9A826)      // Warm Orange
    val Secondary = Color(0xFF6AC692)    // Soft Green
    val Background = Color(0xFFFAF9F6)   // Off-White
    val TextPrimary = Color(0xFF2D2D2D)  // Almost Black
    val TextSecondary = Color(0xFF767676) // Medium Gray
    val AccentBlue = Color(0xFF5EBBF6)   // Light Blue
    val AccentPink = Color(0xFFF797C8)   // Light Pink
    val AccentYellow = Color(0xFFFFD166) // Mellow Yellow
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResepkitaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ModernAppColors.Background
                ) {
                    ModernRecipeApp()
                }
            }
        }
    }
}

data class RecipeTag(
    val name: String,
    val color: Color,
    val icon: Int? = null
)

data class ModernRecipe(
    val id: Int,
    val name: String,
    val description: String,
    val prepTime: String,
    val cookTime: String,
    val difficulty: String = "Medium",
    val tags: List<RecipeTag> = emptyList(),
    val ingredients: List<String>,
    val steps: List<String>,
    val imageId: Int,
    val authorName: String? = null,
    val rating: Float = 4.5f,
    val reviewCount: Int = (10..200).random()
)

// Sample recipe data
val modernRecipes = listOf(
    ModernRecipe(
        id = 1,
        name = "Stuffed Chicken",
        description = "Delicious chicken breasts stuffed with cheese and wrapped in bacon",
        prepTime = "15 min",
        cookTime = "40 min",
        tags = listOf(
            RecipeTag("Healthy", ModernAppColors.Secondary),
            RecipeTag("Popular", ModernAppColors.AccentYellow),
            RecipeTag("Low Carb", ModernAppColors.AccentBlue)
        ),
        ingredients = listOf(
            "4 boneless skinless chicken breasts",
            "4 oz. cream cheese, softened",
            "1/2 cup grated Parmesan",
            "2 cloves garlic, minced",
            "1 tsp. dried oregano",
            "Salt and freshly ground black pepper",
            "8 slices bacon"
        ),
        steps = listOf(
            "Preheat oven to 400°F. Line a large baking sheet with foil.",
            "Cut a pocket into each chicken breast, then stuff with cream cheese mixture.",
            "Wrap each chicken breast in 2 slices of bacon and secure with toothpicks.",
            "Bake until chicken is cooked through, about 35-40 minutes.",
            "If bacon isn't crispy enough, broil for 2-4 minutes more."
        ),
        imageId = R.drawable.carbonara
    ),
    ModernRecipe(
        id = 2,
        name = "Pasta Primavera",
        description = "Fresh vegetables and pasta in a light creamy sauce",
        prepTime = "10 min",
        cookTime = "20 min",
        tags = listOf(
            RecipeTag("Vegetarian", ModernAppColors.Secondary),
            RecipeTag("Quick", ModernAppColors.AccentPink)
        ),
        ingredients = listOf(
            "8 oz. fettuccine pasta",
            "1 cup broccoli florets",
            "1 red bell pepper, sliced",
            "1 yellow squash, sliced",
            "2 tbsp olive oil",
            "3 cloves garlic, minced",
            "1/2 cup heavy cream",
            "1/4 cup grated Parmesan"
        ),
        steps = listOf(
            "Cook pasta according to package directions.",
            "Sauté vegetables in olive oil until tender-crisp.",
            "Add garlic and cook for 30 seconds.",
            "Stir in cream and bring to a simmer.",
            "Toss with pasta and Parmesan cheese."
        ),
        imageId = R.drawable.chickenalfredo
    ),
    ModernRecipe(
        id = 3,
        name = "Beef Stir Fry",
        description = "Quick and flavorful beef with vegetables",
        prepTime = "15 min",
        cookTime = "10 min",
        tags = listOf(
            RecipeTag("Quick", ModernAppColors.AccentPink),
            RecipeTag("High Protein", ModernAppColors.AccentBlue)
        ),
        ingredients = listOf(
            "1 lb flank steak, thinly sliced",
            "2 cups broccoli florets",
            "1 red bell pepper, sliced",
            "1 carrot, julienned",
            "3 tbsp soy sauce",
            "1 tbsp hoisin sauce",
            "2 cloves garlic, minced",
            "1 tbsp fresh ginger, grated"
        ),
        steps = listOf(
            "Marinate beef in soy sauce, hoisin, garlic, and ginger.",
            "Stir-fry beef in hot wok until browned, then remove.",
            "Stir-fry vegetables until tender-crisp.",
            "Return beef to wok and toss everything together.",
            "Serve hot over steamed rice."
        ),
        imageId = R.drawable.beefwellington
    ),
    ModernRecipe(
        id = 4,
        name = "Muffins with Cocoa",
        description = "Delicious chocolate muffins with cocoa cream topping",
        prepTime = "20 min",
        cookTime = "25 min",
        difficulty = "Easy",
        tags = listOf(
            RecipeTag("Dessert", ModernAppColors.AccentPink),
            RecipeTag("Popular", ModernAppColors.AccentYellow)
        ),
        ingredients = listOf(
            "1 1/2 cups all-purpose flour",
            "3/4 cup unsweetened cocoa powder",
            "1 cup granulated sugar",
            "1 tsp baking soda",
            "1/2 tsp salt",
            "1 cup buttermilk",
            "1/2 cup vegetable oil",
            "2 large eggs",
            "2 tsp vanilla extract"
        ),
        steps = listOf(
            "Preheat oven to 350°F. Line muffin tin with paper liners.",
            "Whisk together dry ingredients in a large bowl.",
            "In another bowl, mix wet ingredients until combined.",
            "Fold wet ingredients into dry until just combined.",
            "Fill muffin cups 2/3 full and bake for 20-25 minutes."
        ),
        imageId = R.drawable.fishandchips,
        authorName = "Emma Stone"
    ),
    ModernRecipe(
        id = 5,
        name = "Beef Doner Wrap",
        description = "Seasoned beef with fresh vegetables in a warm flatbread",
        prepTime = "25 min",
        cookTime = "15 min",
        tags = listOf(
            RecipeTag("Street Food", ModernAppColors.AccentYellow),
            RecipeTag("Dinner", ModernAppColors.Secondary)
        ),
        ingredients = listOf(
            "1 lb ground beef",
            "2 tsp paprika",
            "1 tsp cumin",
            "1/2 tsp coriander",
            "4 flatbreads or pitas",
            "1 tomato, diced",
            "1/2 cucumber, sliced",
            "1/4 red onion, thinly sliced",
            "Tzatziki sauce"
        ),
        steps = listOf(
            "Mix beef with spices and form into a loaf.",
            "Cook in oven at 375°F for 30-40 minutes.",
            "Slice beef thinly and warm flatbreads.",
            "Assemble wraps with meat, vegetables, and sauce.",
            "Wrap tightly and serve immediately."
        ),
        imageId = R.drawable.beefwellington
    )
)

@Composable
fun ModernRecipeApp() {
    val navController = rememberNavController()
    var showOnboarding by rememberSaveable { mutableStateOf(true) }

    if (showOnboarding) {
        ModernWelcomeScreen(onGetStartedClick = { showOnboarding = false })
    } else {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                ModernHomeScreen(
                    onRecipeClick = { recipeId ->
                        navController.navigate("recipe_detail/$recipeId")
                    }
                )
            }
            composable("recipe_detail/{recipeId}") { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull() ?: 1
                val recipe = modernRecipes.find { it.id == recipeId }
                recipe?.let {
                    ModernRecipeDetailScreen(
                        recipe = it,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@Composable
fun ModernWelcomeScreen(onGetStartedClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ModernAppColors.Primary)
    ) {
        // Decorative food illustrations floating around (represented here by circles)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.3f))
                .align(Alignment.TopStart)
                .padding(top = 80.dp, start = 40.dp)
        )

        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
                .align(Alignment.TopEnd)
                .padding(top = 150.dp, end = 60.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logs),
                contentDescription = "Chef Illustration",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "TASTORY",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Discover delicious recipes for every occasion",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = ModernAppColors.Primary
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "PREMIUM RECIPES",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f),
                letterSpacing = 2.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernHomeScreen(onRecipeClick: (Int) -> Unit) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ModernAppColors.Background,
                    titleContentColor = ModernAppColors.TextPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "What would you like to Cook?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = ModernAppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("Search for your recipe") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = ModernAppColors.TextSecondary
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ModernAppColors.TextSecondary.copy(alpha = 0.5f),
                    unfocusedBorderColor = ModernAppColors.TextSecondary.copy(alpha = 0.3f),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { /* Handle search */ })
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Today's Recipe Section
            Text(
                text = "Today's recipe",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = ModernAppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Horizontal Recipe Cards
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(end = 16.dp)
            ) {
                items(modernRecipes.take(2)) { recipe ->
                    FeaturedRecipeCard(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Recommended Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recommended",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = ModernAppColors.TextPrimary
                )

                Text(
                    text = "See All",
                    style = MaterialTheme.typography.bodyMedium,
                    color = ModernAppColors.TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Vertical Recipe List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(modernRecipes.drop(2)) { recipe ->
                    RecipeListItem(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedRecipeCard(recipe: ModernRecipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = recipe.imageId),
                    contentDescription = recipe.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${recipe.prepTime} prep | ${recipe.cookTime} cooking",
                    style = MaterialTheme.typography.bodySmall,
                    color = ModernAppColors.TextSecondary,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun RecipeListItem(recipe: ModernRecipe, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Recipe Image
            Image(
                painter = painterResource(id = recipe.imageId),
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
            )

            // Recipe Information
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                if (recipe.authorName != null) {
                    Text(
                        text = "by ${recipe.authorName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = ModernAppColors.TextSecondary
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${recipe.prepTime} prep",
                        style = MaterialTheme.typography.bodySmall,
                        color = ModernAppColors.TextSecondary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(ModernAppColors.TextSecondary.copy(alpha = 0.5f))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${recipe.reviewCount} reviews",
                        style = MaterialTheme.typography.bodySmall,
                        color = ModernAppColors.TextSecondary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernRecipeDetailScreen(recipe: ModernRecipe, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
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
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                // Hero Image
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    ) {
                        Image(
                            painter = painterResource(id = recipe.imageId),
                            contentDescription = recipe.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Gradient overlay for better text visibility
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Black.copy(alpha = 0.6f),
                                            Color.Transparent,
                                            Color.Transparent
                                        )
                                    )
                                )
                        )
                    }
                }

                // Recipe Title and Info
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = recipe.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = ModernAppColors.TextPrimary
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Recipe tags
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(recipe.tags) { tag ->
                                RecipeTagChip(tag = tag)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Ingredients section
                        Text(
                            text = "Ingredients:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = ModernAppColors.TextPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        recipe.ingredients.forEach { ingredient ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(ModernAppColors.Primary)
                                        .align(Alignment.CenterVertically)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = ingredient,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = ModernAppColors.TextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Directions section
                        Text(
                            text = "Directions:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = ModernAppColors.TextPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Recipe steps
                items(recipe.steps.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(ModernAppColors.Primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = recipe.steps[index],
                            style = MaterialTheme.typography.bodyMedium,
                            color = ModernAppColors.TextSecondary,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    if (index < recipe.steps.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 60.dp, end = 16.dp),
                            thickness = 1.dp,
                            color = ModernAppColors.TextSecondary.copy(alpha = 0.2f)
                        )
                    }
                }

                // Bottom spacing
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun RecipeTagChip(tag: RecipeTag) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(tag.color.copy(alpha = 0.2f))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tag.name,
            style = MaterialTheme.typography.bodySmall,
            color = tag.color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    ResepkitaTheme {
        ModernWelcomeScreen(onGetStartedClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ResepkitaTheme {
        ModernHomeScreen(onRecipeClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    ResepkitaTheme {
        ModernRecipeDetailScreen(
            recipe = modernRecipes[0],  // Using the first recipe from the sample data
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeTagChipPreview() {
    ResepkitaTheme {
        RecipeTagChip(
            tag = RecipeTag("Healthy", ModernAppColors.Secondary)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeaturedRecipeCardPreview() {
    ResepkitaTheme {
        FeaturedRecipeCard(
            recipe = modernRecipes[0],
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListItemPreview() {
    ResepkitaTheme {
        RecipeListItem(
            recipe = modernRecipes[2],
            onClick = {}
        )
    }
}