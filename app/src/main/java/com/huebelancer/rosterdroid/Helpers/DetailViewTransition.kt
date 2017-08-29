package com.huebelancer.rosterdroid.Helpers

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.transition.TransitionValues
import android.util.Log
import android.view.View
import android.view.ViewGroup

/**
 * Created by mahuebel on 8/25/17.
 */
class DetailViewTransition : Transition() {

    val TAG = this.javaClass.simpleName

    val PROPNAME_BACKGROUND = "detailviewtransition:DetailViewTransition:background"

    override fun captureStartValues(p0: TransitionValues?) {
        captureValues(p0)
    }

    override fun captureEndValues(p0: TransitionValues?) {
        captureValues(p0)
    }

    private fun captureValues(transitionValues: TransitionValues?) {
        val view: View? = transitionValues?.view
        transitionValues?.values?.put(PROPNAME_BACKGROUND, view?.background)
    }

    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {

        if (null == startValues || null == endValues) {
            return null
        }

        val view = endValues.view

        Log.d(TAG, startValues.toString())

        try {
            val startColor = startValues.values[PROPNAME_BACKGROUND] as ColorDrawable
            val endColor = endValues.values[PROPNAME_BACKGROUND] as ColorDrawable

            Log.d(TAG, "succeeded in try/catch")

            val animator: ValueAnimator = ValueAnimator.ofObject(
                    ArgbEvaluator(), startColor.color, endColor.color
            )
            animator.addUpdateListener { animation ->
                val value = animation.animatedValue
                // Each time the ValueAnimator produces a new frame in the animation, change
                // the background color of the target. Ensure that the value isn't null.
                if (null != value) {
                    view.setBackgroundColor(value as Int)
                }
            }

            return animator

        } catch (e: Exception) {
            e.printStackTrace()
        }


        return super.createAnimator(sceneRoot, startValues, endValues)
    }

}