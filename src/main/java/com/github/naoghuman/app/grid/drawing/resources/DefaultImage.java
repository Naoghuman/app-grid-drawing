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
import javafx.scene.image.WritableImage;

/**
 * TODO
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
final class DefaultImage {
    
    private WritableImage writableImage;

    public WritableImage getImage() {
        LoggerFacade.getDefault().debug(ImageReader.class, "DefaultImage#getImage(): WritableImage"); // NOI18N
        
        return writableImage;
    }
    
    public void write(final String msg) {
        LoggerFacade.getDefault().debug(ImageReader.class, "DefaultImage#load(write)"); // NOI18N
        
        // TODO WritableImage
        DefaultGridDrawingValidator.requireNonNull(msg);
        
    }
    
}
