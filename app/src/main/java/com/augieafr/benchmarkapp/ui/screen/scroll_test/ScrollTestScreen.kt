package com.augieafr.benchmarkapp.ui.screen.scroll_test

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.augieafr.benchmarkapp.data.model.response.UnsplashResponse
import com.augieafr.benchmarkapp.ui.component.LargeSpace
import com.augieafr.benchmarkapp.ui.component.MediumLargeSpace
import com.augieafr.benchmarkapp.ui.component.MediumSpace
import com.augieafr.benchmarkapp.ui.component.SmallSpace

@Composable
fun ScrollTestScreen(
    modifier: Modifier = Modifier,
    viewModel: ScrollTestViewModel = viewModel()
) {
    val photos = viewModel.photos.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = photos.itemCount,
            key = photos.itemKey { it.id ?: "" }
        ) { index ->
            val photo = photos[index]
            photo?.let {
                EnhancedPhotoCard(
                    photo = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                MediumSpace()
            }
        }

        // Loading state
        when (photos.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error loading more photos",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            else -> {}
        }
    }

    // Initial loading state
    if (photos.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun EnhancedPhotoCard(
    photo: UnsplashResponse,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            // Sponsorship banner if exists
            photo.sponsorship?.let { sponsorship ->
                SponsorshipBanner(sponsorship = sponsorship)
            }

            LargeSpace()

            // User profile section
            UserProfileSection(user = photo.user)

            SmallSpace()

            // Main photo
            Box {
                AsyncImage(
                    model = photo.urls?.regular,
                    contentDescription = photo.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )

                // Color indicator
                photo.color?.let { color ->
                    ColorIndicator(
                        color = color,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                    )
                }
            }

            // Photo metadata and actions
            PhotoMetadataSection(photo = photo)

            // Photo description
            photo.description?.let { description ->
                PhotoDescription(description = description)
            }

            SmallSpace()

            // Photo stats and actions
            PhotoActionsSection(photo = photo)

            LargeSpace()
        }
    }
}

@Composable
private fun SponsorshipBanner(sponsorship: com.augieafr.benchmarkapp.data.model.response.Sponsorship) {
    sponsorship.tagline?.let { tagline ->
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Sponsored",
                    tint = MaterialTheme.colorScheme.primary
                )
                SmallSpace()
                Text(
                    text = "Sponsored: $tagline",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun UserProfileSection(user: com.augieafr.benchmarkapp.data.model.response.User?) {
    user?.let {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User avatar
                AsyncImage(
                    model = it.profileImage?.medium ?: it.profileImage?.small,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape),
                    contentScale = ContentScale.Crop
                )

                MediumLargeSpace()

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = it.name ?: it.username ?: "Unknown",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )

                    // User stats
                    Row {
                        UserStat(label = "Photos", value = it.totalPhotos)
                        LargeSpace()
                        UserStat(label = "Likes", value = it.totalLikes)
                        LargeSpace()
                        UserStat(label = "Collections", value = it.totalCollections)
                    }
                }

                // For hire badge
                if (it.forHire == true) {
                    Badge(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                            text = "For Hire",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }

            SmallSpace()

            it.bio?.let { bio ->
                Text(
                    text = bio,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun UserStat(label: String, value: Int?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value?.toString() ?: "0",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ColorIndicator(color: String, modifier: Modifier = Modifier) {
    val colorInt = Color(color.toColorInt())
    Box(
        modifier = modifier
            .size(32.dp)
            .background(colorInt, CircleShape)
            .border(2.dp, Color.White, CircleShape)
    )
}

@Composable
private fun PhotoMetadataSection(photo: UnsplashResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Dimensions
        if (photo.width != null && photo.height != null) {
            MetadataChip(
                label = "Size",
                value = "${photo.width} × ${photo.height}"
            )
        }

        // Creation date
        photo.createdAt?.let { date ->
            MetadataChip(
                label = "Created",
                value = formatDate(date)
            )
        }

        // Asset type
        photo.assetType?.let { type ->
            MetadataChip(
                label = "Type",
                value = type.uppercase()
            )
        }
    }
}

@Composable
private fun MetadataChip(label: String, value: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun PhotoDescription(description: String) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun PhotoActionsSection(photo: UnsplashResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Likes section
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Handle like */ }) {
                Icon(
                    imageVector = if (photo.likedByUser == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (photo.likedByUser == true) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = photo.likes?.toString() ?: "0",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Action buttons
        Row {
            IconButton(onClick = { /* Handle download */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Download",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(onClick = { /* Handle share */ }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun formatDate(dateString: String): String {
    return try {
        // Simple date formatting - you can enhance this with proper date parsing
        dateString.substring(0, 10) // Extract YYYY-MM-DD part
    } catch (e: Exception) {
        "Unknown"
    }
}