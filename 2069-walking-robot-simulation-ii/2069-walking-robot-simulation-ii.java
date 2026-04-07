class Robot {
    private int width, height, perimeter;
    private int pos = 0;
    private boolean moved = false;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        // Total unique boundary cells is (w + h - 2) * 2
        this.perimeter = 2 * (width + height - 2);
    }

    public void step(int num) {
        moved = true;
        // Modular arithmetic handles any number of laps
        pos = (pos + num) % perimeter;
    }

    public int[] getPos() {
        if (pos < width) {
            return new int[]{pos, 0}; // Bottom edge
        } else if (pos < width + height - 1) {
            return new int[]{width - 1, pos - (width - 1)}; // Right edge
        } else if (pos < 2 * width + height - 2) {
            return new int[]{width - 1 - (pos - (width + height - 2)), height - 1}; // Top edge
        } else {
            return new int[]{0, perimeter - pos}; // Left edge
        }
    }

    public String getDir() {
        // Special Case: At origin (0,0) after at least one movement
        if (moved && pos == 0) return "South";
        
        // Initial state or positions along the first edge
        if (pos > 0 && pos < width) return "East";
        // Positions along the right edge
        if (pos >= width && pos < width + height - 1) return "North";
        // Positions along the top edge
        if (pos >= width + height - 1 && pos < 2 * width + height - 2) return "West";
        // Positions along the left edge (excluding the origin reset handled above)
        if (pos >= 2 * width + height - 2 && pos < perimeter) return "South";
        
        return "East"; // Default for initial state: pos=0, moved=false
    }
}