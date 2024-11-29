import java.io.Serializable

data class Recipe(
    val name: String,
    val description: String,
    val imageResId: Int,
    val ingredients: String,
    val preparationSteps: String
) : Serializable
