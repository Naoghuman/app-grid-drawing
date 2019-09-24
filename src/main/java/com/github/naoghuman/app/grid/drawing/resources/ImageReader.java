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
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.io.File;
import javafx.scene.image.Image;

/**
 * TODO
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
final class ImageReader {

    /**
     * 
     * @param   file
     * @return
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     * @author  Naoghuman
     */
    public static Image read(final File file) {
        LoggerFacade.getDefault().debug(ImageReader.class, "ImageReader#read(File): Image"); // NOI18N
        
        DefaultGridDrawingValidator.requireNonNull(file); 
        
        Image image;
        try {
            image = new Image(file.toURI().toString());
        } catch (Exception ex) {
            LoggerFacade.getDefault().warn(ImageReader.class, String.format("Can't load the image with path: [%s].", file.toURI().toString()), ex); // NOI18N
            
            final DefaultImage defaultImage = new DefaultImage();
            defaultImage.write(String.format("Can't load the image with path:\n[%s].", file.toURI().toString())); // NOI18N
            image = defaultImage.getImage();
        }
        
        return image;
    }
    
}
