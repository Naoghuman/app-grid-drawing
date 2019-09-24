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
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * TODO
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
final class ImageWriter {

    /**
     * 
     * @param   directory
     * @param   image
     * @return
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     * @author  Naoghuman
     */
    public static void write(final File file, final Image image) {
        LoggerFacade.getDefault().debug(ImageReader.class, "ImageWriter#write(File, Image)"); // NOI18N
        
        DefaultGridDrawingValidator.requireNonNull(file);
        DefaultGridDrawingValidator.requireNonNull(image);
        
        try {
            final String fileName = file.getName();
            final String suffix   = ImageFormat.getSuffix(fileName);
            
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), suffix, file);
        } catch (IOException ex) {
            LoggerFacade.getDefault().error(ImageWriter.class, String.format("Can't save the image to: [%s].", file.toURI().toString()), ex); // NOI18N
        }
    }
    
}
