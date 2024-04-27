/** @type {import('tailwindcss').Config} */
module.exports = {
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    colors: {
      primary: '#CE8545',
      secondary: '#201E19',
      white: '#fff',
      melocoton: '#FEF0D3',
      accent: '#64748B',
      ligth_orange: '#F2A665',
      error: '#EF4444',
      warning: '#F59E0B',
      info: '#2563EB',
      success: '#10B981',
    }
  },
  variants: {
    extend: {},
  },
  plugins: [],
}