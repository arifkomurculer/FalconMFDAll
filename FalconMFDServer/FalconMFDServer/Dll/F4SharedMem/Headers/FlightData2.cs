using System;
using System.Runtime.InteropServices;

namespace F4SharedMem.Headers
{
    [ComVisible(false)]
    [Serializable]
    [StructLayout(LayoutKind.Sequential, Pack = 8)]
    public struct FlightData2
    {
        public const int RWRINFO_SIZE = 512;
        public const int MAX_CALLSIGNS = 32;

        // VERSION 1
        float nozzlePos2;   // Ownship engine nozzle2 percent open (0-100)
        float rpm2;         // Ownship engine rpm2 (Percent 0-103)
        float ftit2;        // Ownship Forward Turbine Inlet Temp2 (Degrees C)
        float oilPressure2; // Ownship Oil Pressure2 (Percent 0-100)
        byte navMode;  // current mode selected for HSI/eHSI (added in BMS4)
        float aauz; // Ownship barometric altitude given by AAU (depends on calibration)
        [MarshalAs(UnmanagedType.ByValArray, SizeConst = (int)TacanSources.NUMBER_OF_SOURCES)]
        byte[] tacanInfo;      // Tacan band/mode settings for UFC and AUX COMM

        // VERSION 2
        int AltCalReading;	// barometric altitude calibration (depends on CalType)
        int altBits;		// various altimeter bits, see AltBits enum for details
        int powerBits;		// Ownship power bus / generator states, see PowerBits enum for details
        int blinkBits;		// Cockpit indicator lights blink status, see BlinkBits enum for details
        // NOTE: these bits indicate only *if* a lamp is blinking, in addition to the
        // existing on/off bits. It's up to the external program to implement the
        // *actual* blinking.
        int cmdsMode;		// Ownship CMDS mode state, see CmdsModes enum for details
        int BupUhfPreset;	// BUP UHF channel preset

        // VERSION 3
        int BupUhfFreq;		// BUP UHF channel frequency
        float cabinAlt;		// Ownship cabin altitude
        float hydPressureA;	// Ownship Hydraulic Pressure A
        float hydPressureB;	// Ownship Hydraulic Pressure B
        int currentTime;	// Current time in seconds (max 60 * 60 * 24)
        short vehicleACD;	// Ownship ACD index number, i.e. which aircraft type are we flying.
        int VersionNum2;		// Version of FlightData2 mem area

        // VERSION 4
        float fuelFlow2;    // Ownship fuel flow2 (Lbs/Hour)

        // VERSION 5 / 8
        [MarshalAs(UnmanagedType.ByValArray, SizeConst = RWRINFO_SIZE)]
        byte[] RwrInfo;     //[512] New RWR Info
        float lefPos;       // Ownship LEF position
        float tefPos;       // Ownship TEF position

        // VERSION 6
        float vtolPos;      // Ownship VTOL exhaust angle

        // VERSION 9
        byte pilotsOnline;                  // Number of pilots in an MP session

        [MarshalAs(UnmanagedType.ByValArray, SizeConst = MAX_CALLSIGNS)]
        public Callsign_LineOfText[] pilotsCallsign;        // [MAX_CALLSIGNS][CALLSIGN_LEN] List of pilots callsign connected to an MP session

        [MarshalAs(UnmanagedType.ByValArray, SizeConst = MAX_CALLSIGNS)]
        byte[] pilotsStatus;                // [MAX_CALLSIGNS] Status of the MP pilots, see enum FlyStates
    }

}
