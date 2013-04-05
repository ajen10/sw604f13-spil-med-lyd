package dk.aau.cs.giraf.cars.sound;

import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.media.AudioFormat;


public class RecorderThread extends Thread {
	public boolean recording;  //variable to start or stop recording
	public int frequency; //the public variable that contains the frequency value "heard", it is updated continually while the thread is running.
	public int highestHumanPitch = 10000; //Determin the highst frequency a human can make to get rid of false data
	public int voiceSensetivity = 10000;  //Determin the "volume" that that has to be recorded before the input data is valid
	private int currentFrequency = -1;
	int count = 0;
	public RecorderThread() {
		
	}
	

	@Override
	public void run() {
		System.out.println("recorderThread started");
		AudioRecord recorder;
		int p;
		short[] audioData;
		int bufferSize;
		int Samplerate = 44100;

		bufferSize=AudioRecord.getMinBufferSize(Samplerate,AudioFormat.CHANNEL_IN_MONO,
				AudioFormat.ENCODING_PCM_16BIT)*2; //get the buffer size to use with this audio record  

		recorder = new AudioRecord (AudioSource.MIC,Samplerate,AudioFormat.CHANNEL_IN_MONO,
				AudioFormat.ENCODING_PCM_16BIT,bufferSize); //instantiate the AudioRecorder


		recording=true; //variable to use start or stop recording
		audioData = new short [bufferSize]; //short array that pcm data is put into.


		while (recording) {  //loop while recording is needed

			if (recorder.getState()==android.media.AudioRecord.STATE_INITIALIZED){ // check to see if the recorder has initialized yet.

			}
			if (recorder.getRecordingState()==android.media.AudioRecord.RECORDSTATE_STOPPED){
				recorder.startRecording();  //check to see if the Recorder has stopped or is not recording, and make it record.

			}

			else {

				recorder.read(audioData,0,bufferSize); //read the PCM audio data into the audioData array
				double[] endAudioData = new double [bufferSize*2];                        
				//Now we need to decode the PCM data using the Zero Crossings Method
				//FFT fft1 = new FFT();
				short[] imgAudioData = new short [bufferSize];
				endAudioData = FFT.fft(audioData,imgAudioData, true);

				double[] magnitude = new double [bufferSize-1];
				int highestMagnitude = 0;
				for (p=2;p<(bufferSize-1)*2;p+=2){
					magnitude[p/2-1] = Math.sqrt(endAudioData[p]*endAudioData[p] + endAudioData[p+1]*endAudioData[p+1]);
					//System.out.println("magnitude = " + magnitude[p/2-1]);
					if (magnitude[p/2-1] > magnitude[highestMagnitude]){
						highestMagnitude = p/2-1;
					}
				}
				int i;
				double[] frequency = new double [bufferSize-1];  
				for (i=0 ; i<(bufferSize-1) ; i++){
					frequency[i] = ((i+1) * Samplerate/2) / (bufferSize/2);
				}

				double total = 0;
				double magnitudeTotal = 0;
				for (i=-2 ; i<2 ; i++){
					total += frequency[highestMagnitude+i]*magnitude[highestMagnitude+i];
					magnitudeTotal += magnitude[highestMagnitude+i];
				}

				double averageFreq = total/magnitudeTotal;
				if (averageFreq<highestHumanPitch && magnitudeTotal>voiceSensetivity) {
					currentFrequency = (int) averageFreq;
					//System.out.println("average frequency = " + (int)averageFreq + " magnitude total = " + (int)magnitudeTotal);
				} else {
					currentFrequency = 0;
				}
				
				System.out.println(count++);
				
				GameInfo.setCurrFreq(currentFrequency);
				
			}//else recorder started 

		} //while recording 

		if (recorder.getState()==android.media.AudioRecord.RECORDSTATE_RECORDING) recorder.stop(); //stop the recorder before ending the thread
		recorder.release(); //release the recorders resources
		recorder=null; //set the recorder to be garbage collected.    

	}//run 

}//recorderThread
