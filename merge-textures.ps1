# Merge AC items into main items.png at rows 11-14
Add-Type -AssemblyName System.Drawing

# Load images
$acItems = [System.Drawing.Image]::FromFile("d:\Desktop\AdventureCraft-Dev\bin\ac_items.png")
$mainItems = [System.Drawing.Image]::FromFile("d:\Desktop\AdventureCraft-Dev\bin\gui\items.png")

# Create a new bitmap with the merged result
$merged = New-Object System.Drawing.Bitmap($mainItems.Width, $mainItems.Height)
$graphics = [System.Drawing.Graphics]::FromImage($merged)

# Copy main items.png
$graphics.DrawImage($mainItems, 0, 0)

# Copy AC items to rows 11-14 (y = 11*16 = 176 pixels)
# AC items are in ac_items.png starting at (0,0)
# We'll copy the first 18 items (arranged as 16 items in row 0, then 2 in row 1)
$destY = 11 * 16 # Row 11

# Copy first row (16 items)
$srcRect = New-Object System.Drawing.Rectangle(0, 0, 256, 16)
$graphics.DrawImage($acItems, 0, $destY, $srcRect, [System.Drawing.GraphicsUnit]::Pixel)

# Copy second row (2 items needed)
$destY2 = 12 * 16 # Row 12
$srcRect2 = New-Object System.Drawing.Rectangle(0, 16, 256, 16)
$graphics.DrawImage($acItems, 0, $destY2, $srcRect2, [System.Drawing.GraphicsUnit]::Pixel)

# Save the merged image
$merged.Save("d:\Desktop\AdventureCraft-Dev\bin\gui\items.png", [System.Drawing.Imaging.ImageFormat]::Png)

Write-Output "Merged AC items into items.png at rows 11-12"

# Cleanup
$graphics.Dispose()
$merged.Dispose()
$acItems.Dispose()
$mainItems.Dispose()
