export default {
  content: ['./index.html', './src/**/*.{js,jsx}'],
  theme: {
    extend: {
      colors: {
        primary: '#2D6A5F', 'primary-light': '#E1F5EE', 'primary-dark': '#1A4A40',
        sand: '#FAF8F4', ink: '#2B2723', muted: '#6B6258', line: '#E5DDD0', accent: '#D08770'
      },
      boxShadow: { card: '0 3px 12px rgba(61, 49, 35, 0.05)' }
    }
  },
  plugins: []
}
