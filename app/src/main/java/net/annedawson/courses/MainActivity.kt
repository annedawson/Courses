package net.annedawson.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.annedawson.courses.data.DataSource.courses
import net.annedawson.courses.model.Course
import net.annedawson.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesApp()
        }
    }
}

@Composable
fun CoursesApp() {
    // val context = LocalContext.current
    CoursesTheme {
        CourseList(courses) // DataSource courses member
    }
}

@OptIn(ExperimentalFoundationApi::class)  // LazyVerticalGrid is experimental API you must opt into - may be removed in the future
@Composable
fun CourseList(courseList: List<Course>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(  // to date, LazyVerticalGrid is an experimental API you must opt into - and may be removed in the future
        cells = GridCells.Fixed(2),
        // modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), // the vertical space between grid items
        horizontalArrangement = Arrangement.spacedBy(8.dp)                                                 // see what happens to the UI if you comment out the line above

    ){
        items(courseList) { course ->
            CourseCard(course)
        }
    }
}



@Composable
fun CourseCard(course: Course, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(0.dp), elevation = 4.dp) {
        Row() {
            
            Box {
                Image(
                    painter = painterResource(course.imageResourceId),
                    contentDescription = stringResource(course.stringResourceId),
                    /*modifier = Modifier
                    .width(68.dp)
                    .height(68.dp),*/
                    // The following from the solution
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop  // maintains aspect ratio
                )
            }
            
            Column {
                Text(
                    text = stringResource(id = course.stringResourceId),
                    //text = LocalContext.current.getString(affirmation.stringResourceId),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 8.dp),
                    //style = MaterialTheme.typography.body2
                    fontSize = 10.sp  // to get the text on one line
                )
                Row() {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null, // decorative element
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .width(16.dp)
                            .height(16.dp)
                    )

                    Text(
                        text = (course.count).toString(),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CourseCardPreview() {
    CourseCard (Course(R.string.architecture, 58,R.drawable.architecture))
}