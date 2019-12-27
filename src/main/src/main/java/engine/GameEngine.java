package engine;



/**
 * Jimmy Deng, June 07, 2019
 * GameEngine class creates a basic gameloop
 */
public class GameEngine implements Runnable {

    public static final int TARGET_FPS = 75;

    public static final int TARGET_UPS = 30;

    private final Window window;

    private final Thread gameLoopThread;

    private final Timer timer;

    private final IGameLogic gameLogic;

    private final MouseInput mouseInput;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) throws Exception {
        this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        this.window = new Window(windowTitle, width, height, vSync);
        this.mouseInput = new MouseInput();
        this.gameLogic = gameLogic;
        this.timer = new Timer();
    }

    public void start() {
        String osName = System.getProperty("os.name");
        if ( osName.contains("Mac") ) {
            this.gameLoopThread.run();
        } else {
            this.gameLoopThread.start();
        }
    }

    @Override
    public void run() {
        try {
            this.init();
            this.gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            this.cleanup();
        }
    }

    protected void init() throws Exception {
        this.window.init();
        this.timer.init();
        this.mouseInput.init(this.window);
        this.gameLogic.init(this.window);
    }

    protected void gameLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !this.window.windowShouldClose()) {
            elapsedTime = this.timer.getElapsedTime();
            accumulator += elapsedTime;

            this.input();

            while (accumulator >= interval) {
                this.update(interval);
                accumulator -= interval;
            }

            this.render();

            if (!this.window.isvSync()) {
                this.sync();
            }
        }
    }

    protected void cleanup() {
        this.gameLogic.cleanup();
    }

    // will only use this method if v-sync is set FALSE
    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = this.timer.getLastLoopTime() + loopSlot;
        while (this.timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    protected void input() {
        this.mouseInput.input(this.window);
        this.gameLogic.input(this.window,this.mouseInput);
    }

    protected void update(float interval) {
        this.gameLogic.update(interval,this.mouseInput);
    }

    protected void render() {
        this.gameLogic.render(this.window);
        this.window.update();
    }
}