/**
 * Copyright (C) 2019 - 2019 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.app.grid.drawing.resources;

import com.github.naoghuman.app.grid.drawing.validator.DefaultGridDrawingValidator;

/**
 * TODO
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
enum ImageFormat {
    
    ALL_IMAGES("All Images", "*.*"),    // NOI18N
    GIF(       "gif",        "*.gif"),  // NOI18N
    JPEG(      "jpeg",       "*.jpeg"), // NOI18N
    JPG(       "jpg",        "*.jpg"),  // NOI18N
    PNG(       "png",        "*.png");  // NOI18N
    
    private final String description;
    private final String extension;
    
    ImageFormat(final String description, final String extension) {
        this.description = description;
        this.extension   = extension;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getExtension() {
        return extension;
    }
    
    public static String getSuffix(String name) {
        DefaultGridDrawingValidator.requireNonNullAndNotEmpty(name);
        
        String suffix = PNG.getExtension().substring(2);                  // '*.png'  -> 'png'
        for (ImageFormat imageFormat : values()) {
            if (name.endsWith(imageFormat.getExtension().substring(1))) { // '*.jpeg' -> '.jpeg'
                suffix = imageFormat.getExtension().substring(2);         // '*.gif'  -> 'gif'
                break;
            }
        }
        
        return suffix;
    }
    
}
