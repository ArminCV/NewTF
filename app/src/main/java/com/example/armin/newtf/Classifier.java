package com.example.armin.newtf;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Locale;

public interface Classifier {

    class Recognition {
        /**
         * A unique identifier for what has been recognized. Specific to the class, not the instance of
         * the object.
         */
        private final String id;

        /**
         * Display name for the recognition.
         */
        private final String title;

        /**
         * A sortable score for how good the recognition is relative to others. Higher should be better.
         */
        private final Float confidence;

        public Recognition(
                final String id, final String title, final Float confidence) {
            this.id = id;
            this.title = title;
            this.confidence = confidence;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Float getConfidence() {
            return confidence;
        }

        @Override
        public String toString() {
            String resultString = "";
            if (title != null) {
                resultString = title;
            }
            //to display confidence percent
//            if (confidence != null) {
//                //resultString += String.format("(%.1f%%) ", confidence * 100.0f);
//                resultString += String.format(Locale.US, "%.1f%%", (confidence * 100.0f));
//            }

            return resultString.trim();
        }
    }

    List<Recognition> recognizeImage(Bitmap bitmap);

    void close();
}
