package com.sampson.apichallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sampson.apichallenge.controller.EventsRepository
import com.sampson.apichallenge.model.CheckIn
import com.sampson.apichallenge.model.Events
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.IllegalArgumentException
import java.util.concurrent.Flow

class EventViewModel(private val eventsRepository: EventsRepository): ViewModel() {

    private val allEventsLiveData = MutableLiveData<List<Events>>()
    private val errorLiveData = MutableLiveData<String>()
    private val post = PublishProcessor.create<Unit>()

    val allEvents: LiveData<List<Events>>
    get() = allEventsLiveData

    val error: LiveData<String>
    get() = errorLiveData

    private var disposable = CompositeDisposable()

    fun getAllEvents(){
        disposable.add(eventsRepository.getAllEvents()
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                allEventsLiveData.postValue(it)
            }, {
                error ->
                Log.d("EventViewModel","error: ${error.localizedMessage}")
            }))
    }

    fun postCheckIn(checkIn: CheckIn){
        disposable.add(eventsRepository.postCheckIn(checkIn)
            .subscribeOn(Schedulers.io())
            .subscribe({ post.onNext(Unit)}))
    }
}

class EventsViewModelFactory(private val repository: EventsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)){
            return EventViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}