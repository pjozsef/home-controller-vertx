package com.github.pjozsef.control.command.platform

import com.github.pjozsef.control.command.OS

class WindowsCommander(os: OS) : BaseCommander(os) {

    override val supported: List<String>
        get() = listOf()

    override fun shutDown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun restart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun suspend() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playPause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun next() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun prev() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun volUp() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun volDown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVolume(level: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}